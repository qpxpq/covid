package com.info.view.stack.controller;

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
import com.info.view.stack.model.Stack;
import com.info.view.stack.service.IStackService;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class StackController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IStackService stackService;
	@Autowired
	private IFileUploadService fileUploadService;
	
	
	
	
	
	
	@RequestMapping("findStack")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Stack stack = stackService.findById(id);
		modelMap.addAttribute("pageEntity", stack);
		return "forward:/view/stack/add_stack.jsp";
	}
	@RequestMapping("updateStack")
	public String update(@RequestParam("file")CommonsMultipartFile file,ModelMap modelMap,Stack stack,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			if(file != null){
				FileUpload fileUpload = super.uploadFile(request, file, "");
				if(fileUpload != null){
					fileUploadService.addFileUpload(fileUpload);
					stack.setFileURL(fileUpload.getFileNewName()+"." + fileUpload.getFileSuffix());
					stack.setFileName(fileUpload.getFileOldName()+"." + fileUpload.getFileSuffix());
				}
			}
			stack = initEntity.initUpdateInfo(stack, request.getSession());
			stackService.updateStack(stack);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findStack.do?id="+stack.getResourceId();
		}
		return "redirect:/listStack.do";
	}
	@RequestMapping("delteStack")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		stackService.deteleStack(id);
		return "redirect:/listStack.do";
	}
	@RequestMapping("listStack")
	public String list(ModelMap modelMap,HttpServletRequest request,Stack stack){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.stackService.findPagerList(pagerList,stack);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", stack);
		return "forward:/view/stack/list_stack.jsp";
	}
	@RequestMapping("listSelectStack")
	public String listSelectStack(ModelMap modelMap,HttpServletRequest request,Stack stack){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.stackService.findPagerList(pagerList, stack);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  stack);
		return "forward:/view/stack/select_stack.jsp";
	}
	
	@RequestMapping("addStack")
	public String add(@RequestParam("file")CommonsMultipartFile file,Stack stack,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
			if(file != null){
				FileUpload fileUpload = super.uploadFile(request, file, "");
				if(fileUpload != null){
					fileUploadService.addFileUpload(fileUpload);
					stack.setFileURL(fileUpload.getFileNewName()+"." + fileUpload.getFileSuffix());
					stack.setFileName(fileUpload.getFileOldName()+"." + fileUpload.getFileSuffix());
				}
			}
	    	stack.setResourceId(UUID.randomUUID().toString().replace("-",""));
			stack = initEntity.initAddInfo(stack, request.getSession());
			stackService.addStack(stack);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listStack.do";
	}
	
	
	@RequestMapping("exportStack")
	public String export(HttpServletRequest request,HttpServletResponse response,Stack stack){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.stackService.findPagerList(pagerList,stack);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"stackId","stackName","password","role","stackNo","cardNo","stackMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importStack")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Stack stack) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"stackName","password","role","stackNo","cardNo","stackMobile","sex"},
					Stack.class);
			//List<Stack> stackList = new ArrayList<Stack>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Stack detail = (Stack)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//stackList.add(detail);
					this.stackService.addStack(detail);
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
