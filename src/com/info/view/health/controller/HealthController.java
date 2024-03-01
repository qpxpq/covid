package com.info.view.health.controller;

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
import com.info.view.health.model.Health;
import com.info.view.health.service.IHealthService;
import com.info.view.user.model.User;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class HealthController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IHealthService healthService;
	
	
	
	
	
	
	@RequestMapping("findHealth")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Health health = healthService.findById(id);
		modelMap.addAttribute("pageEntity", health);
		return "forward:/view/health/add_health.jsp";
	}
	@RequestMapping("updateHealth")
	public String update(ModelMap modelMap,Health health,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			health = initEntity.initUpdateInfo(health, request.getSession());
			healthService.updateHealth(health);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findHealth.do?id="+health.getResourceId();
		}
		return "redirect:/listHealth.do";
	}
	@RequestMapping("delteHealth")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		healthService.deteleHealth(id);
		return "redirect:/listHealth.do";
	}
	@RequestMapping("listHealth")
	public String list(ModelMap modelMap,HttpServletRequest request,Health health){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			health.setCreatorId(user.getResourceId());
		}
		pagerList = this.healthService.findPagerList(pagerList,health);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", health);
		return "forward:/view/health/list_health.jsp";
	}
	@RequestMapping("listSelectHealth")
	public String listSelectHealth(ModelMap modelMap,HttpServletRequest request,Health health){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.healthService.findPagerList(pagerList, health);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  health);
		return "forward:/view/health/select_health.jsp";
	}
	
	@RequestMapping("addHealth")
	public String add(Health health,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	health.setResourceId(UUID.randomUUID().toString().replace("-",""));
			health = initEntity.initAddInfo(health, request.getSession());
			healthService.addHealth(health);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listHealth.do";
	}
	
	
	@RequestMapping("exportHealth")
	public String export(HttpServletRequest request,HttpServletResponse response,Health health){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.healthService.findPagerList(pagerList,health);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"healthId","healthName","password","role","healthNo","cardNo","healthMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importHealth")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Health health) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"healthName","password","role","healthNo","cardNo","healthMobile","sex"},
					Health.class);
			//List<Health> healthList = new ArrayList<Health>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Health detail = (Health)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//healthList.add(detail);
					this.healthService.addHealth(detail);
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
