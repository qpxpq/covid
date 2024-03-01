package com.info.view.flow.controller;

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
import com.info.view.flow.model.Flow;
import com.info.view.flow.service.IFlowService;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class FlowController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IFlowService flowService;
	
	
	
	
	
	
	@RequestMapping("findFlow")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Flow flow = flowService.findById(id);
		modelMap.addAttribute("pageEntity", flow);
		return "forward:/view/flow/add_flow.jsp";
	}
	@RequestMapping("updateFlow")
	public String update(ModelMap modelMap,Flow flow,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			flow = initEntity.initUpdateInfo(flow, request.getSession());
			flowService.updateFlow(flow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findFlow.do?id="+flow.getResourceId();
		}
		return "redirect:/listFlow.do";
	}
	@RequestMapping("delteFlow")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		flowService.deteleFlow(id);
		return "redirect:/listFlow.do";
	}
	@RequestMapping("listFlow")
	public String list(ModelMap modelMap,HttpServletRequest request,Flow flow){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.flowService.findPagerList(pagerList,flow);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", flow);
		return "forward:/view/flow/list_flow.jsp";
	}
	@RequestMapping("listSelectFlow")
	public String listSelectFlow(ModelMap modelMap,HttpServletRequest request,Flow flow){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.flowService.findPagerList(pagerList, flow);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  flow);
		return "forward:/view/flow/select_flow.jsp";
	}
	
	@RequestMapping("addFlow")
	public String add(Flow flow,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	flow.setResourceId(UUID.randomUUID().toString().replace("-",""));
			flow = initEntity.initAddInfo(flow, request.getSession());
			flowService.addFlow(flow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listFlow.do";
	}
	
	
	@RequestMapping("exportFlow")
	public String export(HttpServletRequest request,HttpServletResponse response,Flow flow){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.flowService.findPagerList(pagerList,flow);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"flowId","flowName","password","role","flowNo","cardNo","flowMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importFlow")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Flow flow) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"flowName","password","role","flowNo","cardNo","flowMobile","sex"},
					Flow.class);
			//List<Flow> flowList = new ArrayList<Flow>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Flow detail = (Flow)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//flowList.add(detail);
					this.flowService.addFlow(detail);
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
