package com.info.view.work.controller;

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
import com.info.view.work.model.Work;
import com.info.view.work.service.IWorkService;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class WorkController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IWorkService workService;
	
	
	
	
	
	
	@RequestMapping("findWork")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Work work = workService.findById(id);
		modelMap.addAttribute("pageEntity", work);
		return "forward:/view/work/add_work.jsp";
	}
	@RequestMapping("updateWork")
	public String update(ModelMap modelMap,Work work,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			work = initEntity.initUpdateInfo(work, request.getSession());
			workService.updateWork(work);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findWork.do?id="+work.getResourceId();
		}
		return "redirect:/listWork.do";
	}
	@RequestMapping("delteWork")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		workService.deteleWork(id);
		return "redirect:/listWork.do";
	}
	@RequestMapping("listWork")
	public String list(ModelMap modelMap,HttpServletRequest request,Work work){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.workService.findPagerList(pagerList,work);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", work);
		return "forward:/view/work/list_work.jsp";
	}
	@RequestMapping("listSelectWork")
	public String listSelectWork(ModelMap modelMap,HttpServletRequest request,Work work){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.workService.findPagerList(pagerList, work);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  work);
		return "forward:/view/work/select_work.jsp";
	}
	
	@RequestMapping("addWork")
	public String add(Work work,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	work.setResourceId(UUID.randomUUID().toString().replace("-",""));
			work = initEntity.initAddInfo(work, request.getSession());
			workService.addWork(work);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listWork.do";
	}
	
	
	@RequestMapping("exportWork")
	public String export(HttpServletRequest request,HttpServletResponse response,Work work){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.workService.findPagerList(pagerList,work);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"workId","workName","password","role","workNo","cardNo","workMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importWork")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Work work) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"workName","password","role","workNo","cardNo","workMobile","sex"},
					Work.class);
			//List<Work> workList = new ArrayList<Work>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Work detail = (Work)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//workList.add(detail);
					this.workService.addWork(detail);
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
