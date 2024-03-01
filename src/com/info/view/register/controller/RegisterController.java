package com.info.view.register.controller;

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
import com.info.view.register.model.Register;
import com.info.view.register.service.IRegisterService;
import com.info.view.user.model.User;

import net.sf.json.JSONObject;


@Controller
public class RegisterController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IRegisterService registerService;
	
	
	
	
	
	
	@RequestMapping("findRegister")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Register register = registerService.findById(id);
		modelMap.addAttribute("pageEntity", register);
		return "forward:/view/register/add_register.jsp";
	}
	@RequestMapping("updateRegister")
	public String update(ModelMap modelMap,Register register,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			register = initEntity.initUpdateInfo(register, request.getSession());
			registerService.updateRegister(register);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findRegister.do?id="+register.getResourceId();
		}
		return "redirect:/listRegister.do";
	}
	@RequestMapping("delteRegister")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		registerService.deteleRegister(id);
		return "redirect:/listRegister.do";
	}
	@RequestMapping("listRegister")
	public String list(ModelMap modelMap,HttpServletRequest request,Register register){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			register.setCreatorId(user.getResourceId());
		}
		
		pagerList = this.registerService.findPagerList(pagerList,register);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", register);
		return "forward:/view/register/list_register.jsp";
	}
	@RequestMapping("listSelectRegister")
	public String listSelectRegister(ModelMap modelMap,HttpServletRequest request,Register register){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.registerService.findPagerList(pagerList, register);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  register);
		return "forward:/view/register/select_register.jsp";
	}
	
	@RequestMapping("addRegister")
	public String add(Register register,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	register.setResourceId(UUID.randomUUID().toString().replace("-",""));
			register = initEntity.initAddInfo(register, request.getSession());
			registerService.addRegister(register);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listRegister.do";
	}
	
	
	@RequestMapping("exportRegister")
	public String export(HttpServletRequest request,HttpServletResponse response,Register register){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.registerService.findPagerList(pagerList,register);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"registerId","registerName","password","role","registerNo","cardNo","registerMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importRegister")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Register register) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"registerName","password","role","registerNo","cardNo","registerMobile","sex"},
					Register.class);
			//List<Register> registerList = new ArrayList<Register>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Register detail = (Register)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//registerList.add(detail);
					this.registerService.addRegister(detail);
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
