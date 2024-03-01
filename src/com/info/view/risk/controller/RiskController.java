package com.info.view.risk.controller;

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
import com.info.view.risk.model.Risk;
import com.info.view.risk.service.IRiskService;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class RiskController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IRiskService riskService;
	
	
	
	
	
	
	@RequestMapping("findRisk")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Risk risk = riskService.findById(id);
		modelMap.addAttribute("pageEntity", risk);
		return "forward:/view/risk/add_risk.jsp";
	}
	@RequestMapping("updateRisk")
	public String update(ModelMap modelMap,Risk risk,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			risk = initEntity.initUpdateInfo(risk, request.getSession());
			riskService.updateRisk(risk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findRisk.do?id="+risk.getResourceId();
		}
		return "redirect:/listRisk.do";
	}
	@RequestMapping("delteRisk")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		riskService.deteleRisk(id);
		return "redirect:/listRisk.do";
	}
	@RequestMapping("listRisk")
	public String list(ModelMap modelMap,HttpServletRequest request,Risk risk){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.riskService.findPagerList(pagerList,risk);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", risk);
		return "forward:/view/risk/list_risk.jsp";
	}
	@RequestMapping("listSelectRisk")
	public String listSelectRisk(ModelMap modelMap,HttpServletRequest request,Risk risk){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.riskService.findPagerList(pagerList, risk);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  risk);
		return "forward:/view/risk/select_risk.jsp";
	}
	
	@RequestMapping("addRisk")
	public String add(Risk risk,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	risk.setResourceId(UUID.randomUUID().toString().replace("-",""));
			risk = initEntity.initAddInfo(risk, request.getSession());
			riskService.addRisk(risk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listRisk.do";
	}
	
	
	@RequestMapping("exportRisk")
	public String export(HttpServletRequest request,HttpServletResponse response,Risk risk){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.riskService.findPagerList(pagerList,risk);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"riskId","riskName","password","role","riskNo","cardNo","riskMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importRisk")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Risk risk) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"riskName","password","role","riskNo","cardNo","riskMobile","sex"},
					Risk.class);
			//List<Risk> riskList = new ArrayList<Risk>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Risk detail = (Risk)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//riskList.add(detail);
					this.riskService.addRisk(detail);
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
