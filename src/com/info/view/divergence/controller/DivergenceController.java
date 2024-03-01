package com.info.view.divergence.controller;

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
import com.info.view.divergence.model.Divergence;
import com.info.view.divergence.service.IDivergenceService;
import com.info.view.user.model.User;

import net.sf.json.JSONObject;


@Controller
public class DivergenceController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IDivergenceService divergenceService;
	
	
	
	
	
	
	@RequestMapping("findDivergence")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Divergence divergence = divergenceService.findById(id);
		modelMap.addAttribute("pageEntity", divergence);
		return "forward:/view/divergence/add_divergence.jsp";
	}
	@RequestMapping("updateDivergence")
	public String update(ModelMap modelMap,Divergence divergence,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			divergence = initEntity.initUpdateInfo(divergence, request.getSession());
			divergenceService.updateDivergence(divergence);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findDivergence.do?id="+divergence.getResourceId();
		}
		return "redirect:/listDivergence.do";
	}
	@RequestMapping("delteDivergence")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		divergenceService.deteleDivergence(id);
		return "redirect:/listDivergence.do";
	}
	@RequestMapping("listDivergence")
	public String list(ModelMap modelMap,HttpServletRequest request,Divergence divergence){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			divergence.setCreatorId(user.getResourceId());
		}
		
		pagerList = this.divergenceService.findPagerList(pagerList,divergence);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", divergence);
		return "forward:/view/divergence/list_divergence.jsp";
	}
	@RequestMapping("listSelectDivergence")
	public String listSelectDivergence(ModelMap modelMap,HttpServletRequest request,Divergence divergence){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.divergenceService.findPagerList(pagerList, divergence);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  divergence);
		return "forward:/view/divergence/select_divergence.jsp";
	}
	
	@RequestMapping("addDivergence")
	public String add(Divergence divergence,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	divergence.setResourceId(UUID.randomUUID().toString().replace("-",""));
			divergence = initEntity.initAddInfo(divergence, request.getSession());
			divergenceService.addDivergence(divergence);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listDivergence.do";
	}
	
	
	@RequestMapping("exportDivergence")
	public String export(HttpServletRequest request,HttpServletResponse response,Divergence divergence){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.divergenceService.findPagerList(pagerList,divergence);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"divergenceId","divergenceName","password","role","divergenceNo","cardNo","divergenceMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importDivergence")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Divergence divergence) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"divergenceName","password","role","divergenceNo","cardNo","divergenceMobile","sex"},
					Divergence.class);
			//List<Divergence> divergenceList = new ArrayList<Divergence>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Divergence detail = (Divergence)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//divergenceList.add(detail);
					this.divergenceService.addDivergence(detail);
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
