package com.info.view.accusation.controller;

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
import com.info.view.accusation.model.Accusation;
import com.info.view.accusation.service.IAccusationService;
import com.info.view.user.model.User;

import net.sf.json.JSONObject;


@Controller
public class AccusationController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IAccusationService accusationService;
	
	
	
	
	
	
	@RequestMapping("findAccusation")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Accusation accusation = accusationService.findById(id);
		modelMap.addAttribute("pageEntity", accusation);
		
		String type = request.getParameter("type");
		if("author".equals(type)){
			return "forward:/view/accusation/author_accusation.jsp";
		}
		
		return "forward:/view/accusation/add_accusation.jsp";
	}
	@RequestMapping("updateAccusation")
	public String update(ModelMap modelMap,Accusation accusation,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			accusation = initEntity.initUpdateInfo(accusation, request.getSession());
			accusationService.updateAccusation(accusation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listAccusation.do";
	}
	@RequestMapping("delteAccusation")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		accusationService.deteleAccusation(id);
		return "redirect:/listAccusation.do";
	}
	@RequestMapping("listAccusation")
	public String list(ModelMap modelMap,HttpServletRequest request,Accusation accusation){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession"); 
			accusation.setUserId(user.getResourceId());
		}
		
		pagerList = this.accusationService.findPagerList(pagerList,accusation);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", accusation);
		return "forward:/view/accusation/list_accusation.jsp";
	}
	@RequestMapping("listSelectAccusation")
	public String listSelectAccusation(ModelMap modelMap,HttpServletRequest request,Accusation accusation){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			accusation.setUserId(user.getResourceId());
		}
		
		pagerList = this.accusationService.findPagerList(pagerList, accusation);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  accusation);
		return "forward:/view/accusation/select_accusation.jsp";
	}
	
	@RequestMapping("addAccusation")
	public String add(Accusation accusation,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	accusation.setResourceId(UUID.randomUUID().toString().replace("-",""));
			accusation = initEntity.initAddInfo(accusation, request.getSession());
			accusationService.addAccusation(accusation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listAccusation.do";
	}
	
	
	@RequestMapping("exportAccusation")
	public String export(HttpServletRequest request,HttpServletResponse response,Accusation accusation){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.accusationService.findPagerList(pagerList,accusation);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"accusationId","accusationName","password","role","accusationNo","cardNo","accusationMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importAccusation")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Accusation accusation) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"accusationName","password","role","accusationNo","cardNo","accusationMobile","sex"},
					Accusation.class);
			//List<Accusation> accusationList = new ArrayList<Accusation>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Accusation detail = (Accusation)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//accusationList.add(detail);
					this.accusationService.addAccusation(detail);
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
