package com.info.view.apply.controller;

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
import com.info.view.apply.model.Apply;
import com.info.view.apply.service.IApplyService;
import com.info.view.user.model.User;

import net.sf.json.JSONObject;


@Controller
public class ApplyController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IApplyService applyService;
	
	
	
	
	
	
	@RequestMapping("findApply")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Apply apply = applyService.findById(id);
		modelMap.addAttribute("pageEntity", apply);
		
		String type = request.getParameter("type");
		if("author".equals(type)){
			return "forward:/view/apply/author_apply.jsp";
		}
		
		return "forward:/view/apply/add_apply.jsp";
	}
	@RequestMapping("updateApply")
	public String update(ModelMap modelMap,Apply apply,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			apply = initEntity.initUpdateInfo(apply, request.getSession());
			applyService.updateApply(apply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listApply.do";
	}
	@RequestMapping("delteApply")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		applyService.deteleApply(id);
		return "redirect:/listApply.do";
	}
	@RequestMapping("listApply")
	public String list(ModelMap modelMap,HttpServletRequest request,Apply apply){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			apply.setUserId(user.getResourceId());
		}
		
		pagerList = this.applyService.findPagerList(pagerList,apply);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", apply);
		return "forward:/view/apply/list_apply.jsp";
	}
	@RequestMapping("listSelectApply")
	public String listSelectApply(ModelMap modelMap,HttpServletRequest request,Apply apply){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			apply.setUserId(user.getResourceId());
		}
		
		pagerList = this.applyService.findPagerList(pagerList, apply);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  apply);
		return "forward:/view/apply/select_apply.jsp";
	}
	
	@RequestMapping("addApply")
	public String add(Apply apply,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	apply.setResourceId(UUID.randomUUID().toString().replace("-",""));
			apply = initEntity.initAddInfo(apply, request.getSession());
			applyService.addApply(apply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listApply.do";
	}
	
	
	@RequestMapping("exportApply")
	public String export(HttpServletRequest request,HttpServletResponse response,Apply apply){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.applyService.findPagerList(pagerList,apply);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"applyId","applyName","password","role","applyNo","cardNo","applyMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importApply")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Apply apply) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"applyName","password","role","applyNo","cardNo","applyMobile","sex"},
					Apply.class);
			//List<Apply> applyList = new ArrayList<Apply>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Apply detail = (Apply)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//applyList.add(detail);
					this.applyService.addApply(detail);
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
