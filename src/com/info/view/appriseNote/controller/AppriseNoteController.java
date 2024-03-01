package com.info.view.appriseNote.controller;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.info.common.controller.BaseController;import com.info.view.log.service.ILogService;
import com.info.common.staticvalue.StaticValue;
import com.info.common.util.DownFileHelper;
import com.info.common.util.ExcelToObject;
import com.info.common.util.ObjectToExcel;
import com.info.common.util.PagerList;
import com.info.view.appriseNote.model.AppriseNote;
import com.info.view.appriseNote.service.IAppriseNoteService;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class AppriseNoteController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IAppriseNoteService appriseNoteService;
	
	
	
	
	
	
	@RequestMapping("findAppriseNote")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		AppriseNote appriseNote = appriseNoteService.findById(id);
		modelMap.addAttribute("pageEntity", appriseNote);
		return "forward:/view/appriseNote/add_appriseNote.jsp";
	}
	@RequestMapping("updateAppriseNote")
	public String update(ModelMap modelMap,AppriseNote appriseNote,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			appriseNote = initEntity.initUpdateInfo(appriseNote, request.getSession());
			appriseNoteService.updateAppriseNote(appriseNote);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findAppriseNote.do?id="+appriseNote.getResourceId();
		}
		return "redirect:/listAppriseNote.do";
	}
	@RequestMapping("delteAppriseNote")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		appriseNoteService.deteleAppriseNote(id);
		return "redirect:/listAppriseNote.do";
	}
	@RequestMapping("listAppriseNote")
	public String list(ModelMap modelMap,HttpServletRequest request,AppriseNote appriseNote){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.appriseNoteService.findPagerList(pagerList,appriseNote);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", appriseNote);
		return "forward:/view/appriseNote/list_appriseNote.jsp";
	}
	@RequestMapping("listSelectAppriseNote")
	public String listSelectAppriseNote(ModelMap modelMap,HttpServletRequest request,AppriseNote appriseNote){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.appriseNoteService.findPagerList(pagerList, appriseNote);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  appriseNote);
		return "forward:/view/appriseNote/select_appriseNote.jsp";
	}
	
	@RequestMapping("addAppriseNote")
	public String add(AppriseNote appriseNote,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	appriseNote.setResourceId(UUID.randomUUID().toString().replace("-",""));
			appriseNote = initEntity.initAddInfo(appriseNote, request.getSession());
			appriseNoteService.addAppriseNote(appriseNote);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listAppriseNote.do";
	}
	
	
	@RequestMapping("exportAppriseNote")
	public String export(HttpServletRequest request,HttpServletResponse response,AppriseNote appriseNote){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.appriseNoteService.findPagerList(pagerList,appriseNote);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"appriseNoteId","appriseNoteName","password","role","appriseNoteNo","cardNo","appriseNoteMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importAppriseNote")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,AppriseNote appriseNote) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"appriseNoteName","password","role","appriseNoteNo","cardNo","appriseNoteMobile","sex"},
					AppriseNote.class);
			//List<AppriseNote> appriseNoteList = new ArrayList<AppriseNote>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					AppriseNote detail = (AppriseNote)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//appriseNoteList.add(detail);
					this.appriseNoteService.addAppriseNote(detail);
				}
				json.put("result", true);
				json.put("msg", "导入成功！导入"+impList.size()+"条数据");
			}else{
				json.put("result", false);
				json.put("msg", "导入失败！导入的excel没数据或解析excel失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			json.put("result", false);
			json.put("msg", e.getMessage());
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json.toString());
		return null;
	}
}
