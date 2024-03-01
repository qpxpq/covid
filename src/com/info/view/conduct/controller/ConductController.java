package com.info.view.conduct.controller;

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
import com.info.view.conduct.model.Conduct;
import com.info.view.conduct.service.IConductService;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class ConductController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IConductService conductService;
	
	
	
	
	
	
	@RequestMapping("findConduct")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Conduct conduct = conductService.findById(id);
		modelMap.addAttribute("pageEntity", conduct);
		return "forward:/view/conduct/add_conduct.jsp";
	}
	@RequestMapping("updateConduct")
	public String update(ModelMap modelMap,Conduct conduct,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			conduct = initEntity.initUpdateInfo(conduct, request.getSession());
			conductService.updateConduct(conduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findConduct.do?id="+conduct.getResourceId();
		}
		return "redirect:/listConduct.do";
	}
	@RequestMapping("delteConduct")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		conductService.deteleConduct(id);
		return "redirect:/listConduct.do";
	}
	@RequestMapping("listConduct")
	public String list(ModelMap modelMap,HttpServletRequest request,Conduct conduct){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.conductService.findPagerList(pagerList,conduct);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", conduct);
		return "forward:/view/conduct/list_conduct.jsp";
	}
	@RequestMapping("listSelectConduct")
	public String listSelectConduct(ModelMap modelMap,HttpServletRequest request,Conduct conduct){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.conductService.findPagerList(pagerList, conduct);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  conduct);
		return "forward:/view/conduct/select_conduct.jsp";
	}
	
	@RequestMapping("addConduct")
	public String add(Conduct conduct,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	conduct.setResourceId(UUID.randomUUID().toString().replace("-",""));
			conduct = initEntity.initAddInfo(conduct, request.getSession());
			conductService.addConduct(conduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listConduct.do";
	}
	
	
	@RequestMapping("exportConduct")
	public String export(HttpServletRequest request,HttpServletResponse response,Conduct conduct){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.conductService.findPagerList(pagerList,conduct);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"conductId","conductName","password","role","conductNo","cardNo","conductMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importConduct")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Conduct conduct) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"conductName","password","role","conductNo","cardNo","conductMobile","sex"},
					Conduct.class);
			//List<Conduct> conductList = new ArrayList<Conduct>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Conduct detail = (Conduct)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//conductList.add(detail);
					this.conductService.addConduct(detail);
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
