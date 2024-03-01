package com.info.view.vaccinum.controller;

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
import com.info.view.vaccinum.model.Vaccinum;
import com.info.view.vaccinum.service.IVaccinumService;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class VaccinumController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IVaccinumService vaccinumService;
	
	
	
	
	
	
	@RequestMapping("findVaccinum")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Vaccinum vaccinum = vaccinumService.findById(id);
		modelMap.addAttribute("pageEntity", vaccinum);
		return "forward:/view/vaccinum/add_vaccinum.jsp";
	}
	@RequestMapping("updateVaccinum")
	public String update(ModelMap modelMap,Vaccinum vaccinum,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			vaccinum = initEntity.initUpdateInfo(vaccinum, request.getSession());
			vaccinumService.updateVaccinum(vaccinum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findVaccinum.do?id="+vaccinum.getResourceId();
		}
		return "redirect:/listVaccinum.do";
	}
	@RequestMapping("delteVaccinum")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		vaccinumService.deteleVaccinum(id);
		return "redirect:/listVaccinum.do";
	}
	@RequestMapping("listVaccinum")
	public String list(ModelMap modelMap,HttpServletRequest request,Vaccinum vaccinum){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.vaccinumService.findPagerList(pagerList,vaccinum);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", vaccinum);
		return "forward:/view/vaccinum/list_vaccinum.jsp";
	}
	@RequestMapping("listSelectVaccinum")
	public String listSelectVaccinum(ModelMap modelMap,HttpServletRequest request,Vaccinum vaccinum){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.vaccinumService.findPagerList(pagerList, vaccinum);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  vaccinum);
		return "forward:/view/vaccinum/select_vaccinum.jsp";
	}
	
	@RequestMapping("addVaccinum")
	public String add(Vaccinum vaccinum,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	vaccinum.setResourceId(UUID.randomUUID().toString().replace("-",""));
			vaccinum = initEntity.initAddInfo(vaccinum, request.getSession());
			vaccinumService.addVaccinum(vaccinum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listVaccinum.do";
	}
	
	
	@RequestMapping("exportVaccinum")
	public String export(HttpServletRequest request,HttpServletResponse response,Vaccinum vaccinum){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.vaccinumService.findPagerList(pagerList,vaccinum);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"vaccinumId","vaccinumName","password","role","vaccinumNo","cardNo","vaccinumMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importVaccinum")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Vaccinum vaccinum) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"vaccinumName","password","role","vaccinumNo","cardNo","vaccinumMobile","sex"},
					Vaccinum.class);
			//List<Vaccinum> vaccinumList = new ArrayList<Vaccinum>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Vaccinum detail = (Vaccinum)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//vaccinumList.add(detail);
					this.vaccinumService.addVaccinum(detail);
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
