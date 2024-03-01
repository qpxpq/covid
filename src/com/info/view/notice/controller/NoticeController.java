package com.info.view.notice.controller;

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
import com.info.view.notice.model.Notice;
import com.info.view.notice.service.INoticeService;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class NoticeController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private INoticeService noticeService;
	
	
	
	
	
	
	@RequestMapping("findNotice")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Notice notice = noticeService.findById(id);
		modelMap.addAttribute("pageEntity", notice);
		return "forward:/view/notice/add_notice.jsp";
	}
	@RequestMapping("updateNotice")
	public String update(ModelMap modelMap,Notice notice,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			notice = initEntity.initUpdateInfo(notice, request.getSession());
			noticeService.updateNotice(notice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findNotice.do?id="+notice.getResourceId();
		}
		return "redirect:/listNotice.do";
	}
	@RequestMapping("delteNotice")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		noticeService.deteleNotice(id);
		return "redirect:/listNotice.do";
	}
	@RequestMapping("listNotice")
	public String list(ModelMap modelMap,HttpServletRequest request,Notice notice){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.noticeService.findPagerList(pagerList,notice);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", notice);
		return "forward:/view/notice/list_notice.jsp";
	}
	@RequestMapping("listSelectNotice")
	public String listSelectNotice(ModelMap modelMap,HttpServletRequest request,Notice notice){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.noticeService.findPagerList(pagerList, notice);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  notice);
		return "forward:/view/notice/select_notice.jsp";
	}
	
	@RequestMapping("addNotice")
	public String add(Notice notice,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	notice.setResourceId(UUID.randomUUID().toString().replace("-",""));
			notice = initEntity.initAddInfo(notice, request.getSession());
			noticeService.addNotice(notice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listNotice.do";
	}
	
	
	@RequestMapping("exportNotice")
	public String export(HttpServletRequest request,HttpServletResponse response,Notice notice){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.noticeService.findPagerList(pagerList,notice);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"noticeId","noticeName","password","role","noticeNo","cardNo","noticeMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importNotice")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Notice notice) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"noticeName","password","role","noticeNo","cardNo","noticeMobile","sex"},
					Notice.class);
			//List<Notice> noticeList = new ArrayList<Notice>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Notice detail = (Notice)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//noticeList.add(detail);
					this.noticeService.addNotice(detail);
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
