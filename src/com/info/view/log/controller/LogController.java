package com.info.view.log.controller;

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
import com.info.view.log.model.Log;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class LogController extends BaseController{
	
	@Autowired
	private ILogService logService;
	
	
	
	
	
	
	@RequestMapping("findLog")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){
		Log log = logService.findById(id);
		modelMap.addAttribute("pageEntity", log);
		return "forward:/view/log/add_log.jsp";
	}
	@RequestMapping("updateLog")
	public String update(ModelMap modelMap,Log log,HttpServletRequest request,HttpServletResponse response){
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			log = initEntity.initUpdateInfo(log, request.getSession());
			logService.updateLog(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findLog.do?id="+log.getResourceId();
		}
		return "redirect:/listLog.do";
	}
	@RequestMapping("delteLog")
	public String delte(String id,HttpServletRequest request){
		logService.deteleLog(id);
		return "redirect:/listLog.do";
	}
	@RequestMapping("listLog")
	public String list(ModelMap modelMap,HttpServletRequest request,Log log){
		PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.logService.findPagerList(pagerList,log);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", log);
		return "forward:/view/log/list_log.jsp";
	}
	@RequestMapping("listSelectLog")
	public String listSelectLog(ModelMap modelMap,HttpServletRequest request,Log log){
		PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.logService.findPagerList(pagerList, log);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  log);
		return "forward:/view/log/select_log.jsp";
	}
	
	@RequestMapping("addLog")
	public String add(Log log,HttpServletRequest request,HttpServletResponse response){
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	log.setResourceId(UUID.randomUUID().toString().replace("-",""));
			log = initEntity.initAddInfo(log, request.getSession());
			logService.addLog(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listLog.do";
	}
	
	
	@RequestMapping("exportLog")
	public String export(HttpServletRequest request,HttpServletResponse response,Log log){
		PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.logService.findPagerList(pagerList,log);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"logId","logName","password","role","logNo","cardNo","logMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importLog")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Log log) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"logName","password","role","logNo","cardNo","logMobile","sex"},
					Log.class);
			//List<Log> logList = new ArrayList<Log>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Log detail = (Log)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//logList.add(detail);
					this.logService.addLog(detail);
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
