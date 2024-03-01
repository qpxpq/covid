package com.info.view.disinfect.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.info.common.util.InitEntity;
import com.info.common.util.ObjectToExcel;
import com.info.common.util.PagerList;
import com.info.view.disinfect.model.Disinfect;
import com.info.view.disinfect.service.IDisinfectService;
import com.info.view.user.model.User;

import net.sf.json.JSONObject;


@Controller
public class DisinfectController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IDisinfectService disinfectService;
	
	
	
	
	
	
	@RequestMapping("findDisinfect")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Disinfect disinfect = disinfectService.findById(id);
		modelMap.addAttribute("pageEntity", disinfect);
		
		String type = request.getParameter("type");
		if("author".equals(type)){
			return "forward:/view/disinfect/author_disinfect.jsp";
		}
		
		return "forward:/view/disinfect/add_disinfect.jsp";
	}
	@RequestMapping("updateDisinfect")
	public String update(ModelMap modelMap,Disinfect disinfect,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			disinfect = initEntity.initUpdateInfo(disinfect, request.getSession());
			disinfectService.updateDisinfect(disinfect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listDisinfect.do";
	}
	@RequestMapping("delteDisinfect")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		disinfectService.deteleDisinfect(id);
		return "redirect:/listDisinfect.do";
	}
	@RequestMapping("listDisinfect")
	public String list(ModelMap modelMap,HttpServletRequest request,Disinfect disinfect){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			disinfect.setUserId(user.getResourceId());
		}
		
		pagerList = this.disinfectService.findPagerList(pagerList,disinfect);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", disinfect);
		return "forward:/view/disinfect/list_disinfect.jsp";
	}
	@RequestMapping("listSelectDisinfect")
	public String listSelectDisinfect(ModelMap modelMap,HttpServletRequest request,Disinfect disinfect){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			disinfect.setUserId(user.getResourceId());
		}
		
		pagerList = this.disinfectService.findPagerList(pagerList, disinfect);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  disinfect);
		return "forward:/view/disinfect/select_disinfect.jsp";
	}
	
	@RequestMapping("addDisinfect")
	public String add(Disinfect disinfect,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	disinfect.setResourceId(UUID.randomUUID().toString().replace("-",""));
			disinfect = initEntity.initAddInfo(disinfect, request.getSession());
			disinfectService.addDisinfect(disinfect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listDisinfect.do";
	}
	
	
	@RequestMapping("exportDisinfect")
	public String export(HttpServletRequest request,HttpServletResponse response,Disinfect disinfect){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.disinfectService.findPagerList(pagerList,disinfect);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"disinfectId","disinfectName","password","role","disinfectNo","cardNo","disinfectMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importDisinfect")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Disinfect disinfect) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"disinfectName","password","role","disinfectNo","cardNo","disinfectMobile","sex"},
					Disinfect.class);
			//List<Disinfect> disinfectList = new ArrayList<Disinfect>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Disinfect detail = (Disinfect)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//disinfectList.add(detail);
					this.disinfectService.addDisinfect(detail);
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
