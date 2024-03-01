package com.info.view.covid.controller;

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
import com.info.view.covid.model.Covid;
import com.info.view.covid.service.ICovidService;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class CovidController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private ICovidService covidService;
	
	
	
	
	
	
	@RequestMapping("findCovid")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Covid covid = covidService.findById(id);
		modelMap.addAttribute("pageEntity", covid);
		return "forward:/view/covid/add_covid.jsp";
	}
	@RequestMapping("updateCovid")
	public String update(ModelMap modelMap,Covid covid,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			covid = initEntity.initUpdateInfo(covid, request.getSession());
			covidService.updateCovid(covid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findCovid.do?id="+covid.getResourceId();
		}
		return "redirect:/listCovid.do";
	}
	@RequestMapping("delteCovid")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		covidService.deteleCovid(id);
		return "redirect:/listCovid.do";
	}
	@RequestMapping("listCovid")
	public String list(ModelMap modelMap,HttpServletRequest request,Covid covid){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.covidService.findPagerList(pagerList,covid);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", covid);
		return "forward:/view/covid/list_covid.jsp";
	}
	@RequestMapping("listSelectCovid")
	public String listSelectCovid(ModelMap modelMap,HttpServletRequest request,Covid covid){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.covidService.findPagerList(pagerList, covid);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  covid);
		return "forward:/view/covid/select_covid.jsp";
	}
	
	@RequestMapping("addCovid")
	public String add(Covid covid,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	covid.setResourceId(UUID.randomUUID().toString().replace("-",""));
			covid = initEntity.initAddInfo(covid, request.getSession());
			covidService.addCovid(covid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listCovid.do";
	}
	
	
	@RequestMapping("exportCovid")
	public String export(HttpServletRequest request,HttpServletResponse response,Covid covid){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.covidService.findPagerList(pagerList,covid);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"covidId","covidName","password","role","covidNo","cardNo","covidMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importCovid")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Covid covid) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"covidName","password","role","covidNo","cardNo","covidMobile","sex"},
					Covid.class);
			//List<Covid> covidList = new ArrayList<Covid>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Covid detail = (Covid)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//covidList.add(detail);
					this.covidService.addCovid(detail);
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
