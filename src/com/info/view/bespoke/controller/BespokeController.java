package com.info.view.bespoke.controller;

import java.util.Date;
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
import com.info.view.bespoke.model.Bespoke;
import com.info.view.bespoke.service.IBespokeService;
import com.info.view.user.model.User;
import com.info.view.user.service.IUserService;

import net.sf.json.JSONObject;


@Controller
public class BespokeController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IBespokeService bespokeService;
	@Autowired
	private IUserService userService;
	
	
	
	
	
	@RequestMapping("findBespoke")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Bespoke bespoke = bespokeService.findById(id);
		modelMap.addAttribute("pageEntity", bespoke);
		
		String type = request.getParameter("type");
		if("author".equals(type)){
			return "forward:/view/bespoke/author_bespoke.jsp";
		}
		if("yjz".equals(type)){
			bespoke.setAccusationType("已结种");
			bespokeService.updateBespoke(bespoke);
			User user = userService.findById(bespoke.getUserId());
			if("第一针".equals(bespoke.getVaccinumType())){
				user.setOneDate(new Date());
				user.setOneType("已结种");
				user.setOneTime(30); //假设第一针到第二针需要30天
			}
			if("第二针".equals(bespoke.getVaccinumType())){
				user.setTwoDate(new Date());
				user.setTwoType("已结种");
				user.setTwoTime(180); //假设第一针到第二针需要180天
			}
			if("第三针".equals(bespoke.getVaccinumType())){
				user.setThreeDate(new Date());
				user.setThreeType("已结种");
			}
			userService.updateUser(user);
			return "redirect:/listBespoke.do";
		}
		return "forward:/view/bespoke/add_bespoke.jsp";
	}
	@RequestMapping("updateBespoke")
	public String update(ModelMap modelMap,Bespoke bespoke,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			bespoke = initEntity.initUpdateInfo(bespoke, request.getSession());
			bespokeService.updateBespoke(bespoke);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listBespoke.do";
	}
	@RequestMapping("delteBespoke")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		bespokeService.deteleBespoke(id);
		return "redirect:/listBespoke.do";
	}
	@RequestMapping("listBespoke")
	public String list(ModelMap modelMap,HttpServletRequest request,Bespoke bespoke){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.bespokeService.findPagerList(pagerList,bespoke);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", bespoke);
		return "forward:/view/bespoke/list_bespoke.jsp";
	}
	@RequestMapping("listSelectBespoke")
	public String listSelectBespoke(ModelMap modelMap,HttpServletRequest request,Bespoke bespoke){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			bespoke.setUserId(user.getResourceId());
		}
		
		pagerList = this.bespokeService.findPagerList(pagerList, bespoke);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  bespoke);
		return "forward:/view/bespoke/select_bespoke.jsp";
	}
	
	@RequestMapping("addBespoke")
	public String add(Bespoke bespoke,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	bespoke.setResourceId(UUID.randomUUID().toString().replace("-",""));
			bespoke = initEntity.initAddInfo(bespoke, request.getSession());
			bespokeService.addBespoke(bespoke);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listBespoke.do";
	}
	
	
	@RequestMapping("exportBespoke")
	public String export(HttpServletRequest request,HttpServletResponse response,Bespoke bespoke){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.bespokeService.findPagerList(pagerList,bespoke);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"bespokeId","bespokeName","password","role","bespokeNo","cardNo","bespokeMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importBespoke")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Bespoke bespoke) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"bespokeName","password","role","bespokeNo","cardNo","bespokeMobile","sex"},
					Bespoke.class);
			//List<Bespoke> bespokeList = new ArrayList<Bespoke>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Bespoke detail = (Bespoke)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//bespokeList.add(detail);
					this.bespokeService.addBespoke(detail);
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
