package com.info.view.antiepidemic.controller;

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
import com.info.view.antiepidemic.model.Antiepidemic;
import com.info.view.antiepidemic.service.IAntiepidemicService;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class AntiepidemicController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IAntiepidemicService antiepidemicService;
	
	
	
	
	
	
	@RequestMapping("findAntiepidemic")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Antiepidemic antiepidemic = antiepidemicService.findById(id);
		modelMap.addAttribute("pageEntity", antiepidemic);
		return "forward:/view/antiepidemic/add_antiepidemic.jsp";
	}
	@RequestMapping("updateAntiepidemic")
	public String update(ModelMap modelMap,Antiepidemic antiepidemic,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			antiepidemic = initEntity.initUpdateInfo(antiepidemic, request.getSession());
			antiepidemicService.updateAntiepidemic(antiepidemic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findAntiepidemic.do?id="+antiepidemic.getResourceId();
		}
		return "redirect:/listAntiepidemic.do";
	}
	@RequestMapping("delteAntiepidemic")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		antiepidemicService.deteleAntiepidemic(id);
		return "redirect:/listAntiepidemic.do";
	}
	@RequestMapping("listAntiepidemic")
	public String list(ModelMap modelMap,HttpServletRequest request,Antiepidemic antiepidemic){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.antiepidemicService.findPagerList(pagerList,antiepidemic);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", antiepidemic);
		return "forward:/view/antiepidemic/list_antiepidemic.jsp";
	}
	@RequestMapping("listSelectAntiepidemic")
	public String listSelectAntiepidemic(ModelMap modelMap,HttpServletRequest request,Antiepidemic antiepidemic){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.antiepidemicService.findPagerList(pagerList, antiepidemic);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  antiepidemic);
		return "forward:/view/antiepidemic/select_antiepidemic.jsp";
	}
	
	@RequestMapping("addAntiepidemic")
	public String add(Antiepidemic antiepidemic,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	antiepidemic.setResourceId(UUID.randomUUID().toString().replace("-",""));
			antiepidemic = initEntity.initAddInfo(antiepidemic, request.getSession());
			antiepidemicService.addAntiepidemic(antiepidemic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listAntiepidemic.do";
	}
	
	
	@RequestMapping("exportAntiepidemic")
	public String export(HttpServletRequest request,HttpServletResponse response,Antiepidemic antiepidemic){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.antiepidemicService.findPagerList(pagerList,antiepidemic);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"antiepidemicId","antiepidemicName","password","role","antiepidemicNo","cardNo","antiepidemicMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importAntiepidemic")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Antiepidemic antiepidemic) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"antiepidemicName","password","role","antiepidemicNo","cardNo","antiepidemicMobile","sex"},
					Antiepidemic.class);
			//List<Antiepidemic> antiepidemicList = new ArrayList<Antiepidemic>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Antiepidemic detail = (Antiepidemic)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//antiepidemicList.add(detail);
					this.antiepidemicService.addAntiepidemic(detail);
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
