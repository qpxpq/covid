package com.info.view.leadership.controller;

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
import com.info.view.news.model.News;
import com.info.view.news.service.INewsService;
import com.info.common.staticvalue.StaticValue;
import com.info.common.util.DownFileHelper;
import com.info.common.util.ExcelToObject;
import com.info.common.util.ObjectToExcel;
import com.info.common.util.PagerList;
import com.info.view.leadership.model.Leadership;
import com.info.view.leadership.service.ILeadershipService;
import com.info.view.user.model.User;
import com.info.view.user.service.IUserService;
import com.info.common.util.InitEntity;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;


@Controller
public class LeadershipController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private ILeadershipService leadershipService;
	@Autowired
	private INewsService newsService;
	@Autowired
	private IUserService userService;
	
	
	
	
	
	
	@RequestMapping("findLeadership")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Leadership leadership = leadershipService.findById(id);
		modelMap.addAttribute("pageEntity", leadership);
		
		String type = request.getParameter("type");
		if("author".equals(type)){
			return "forward:/view/leadership/author_leadership.jsp";
		}
		
		return "forward:/view/leadership/add_leadership.jsp";
	}
	@RequestMapping("updateLeadership")
	public String update(ModelMap modelMap,Leadership leadership,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			leadership = initEntity.initUpdateInfo(leadership, request.getSession());
			leadershipService.updateLeadership(leadership);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listLeadership.do";
	}
	@RequestMapping("delteLeadership")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		leadershipService.deteleLeadership(id);
		return "redirect:/listLeadership.do";
	}
	@RequestMapping("listLeadership")
	public String list(ModelMap modelMap,HttpServletRequest request,Leadership leadership){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.leadershipService.findPagerList(pagerList,leadership);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", leadership);
		return "forward:/view/leadership/list_leadership.jsp";
	}
	@RequestMapping("listSelectLeadership")
	public String listSelectLeadership(ModelMap modelMap,HttpServletRequest request,Leadership leadership){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			leadership.setUserId(user.getResourceId());
		}
		
		pagerList = this.leadershipService.findPagerList(pagerList, leadership);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  leadership);
		return "forward:/view/leadership/select_leadership.jsp";
	}
	
	@RequestMapping("addLeadership")
	public String add(Leadership leadership,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {//String userId,上方改后又还原了
			
			
			
//			User user = userService.findById(userId);
//			String messageName="",messageContext="";
//			
//			News news = new News();
//			
//
//			news.setResourceId(user.getResourceId());//这里没有正确setResourceId
//			System.out.println(user.getResourceId());//在这能得到正确的id
//			System.out.println("888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
//			
//			news.setUserName(user.getUserName());//
//			System.out.println(user.getUserName());
			
			//初始化新增的数据
			//InitEntity initEntity = new InitEntity();
	    	//news.setResourceId(UUID.randomUUID().toString().replace("-",""));
			//news = initEntity.initAddInfo(news, request.getSession());
			//newsService.addNews(news);
			
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	leadership.setResourceId(UUID.randomUUID().toString().replace("-",""));
			leadership = initEntity.initAddInfo(leadership, request.getSession());
			leadershipService.addLeadership(leadership);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listLeadership.do";
	}
	
	
	@RequestMapping("exportLeadership")
	public String export(HttpServletRequest request,HttpServletResponse response,Leadership leadership){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.leadershipService.findPagerList(pagerList,leadership);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"leadershipId","leadershipName","password","role","leadershipNo","cardNo","leadershipMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;



		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importLeadership")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Leadership leadership) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"leadershipName","password","role","leadershipNo","cardNo","leadershipMobile","sex"},
					Leadership.class);
			//List<Leadership> leadershipList = new ArrayList<Leadership>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Leadership detail = (Leadership)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//leadershipList.add(detail);
					this.leadershipService.addLeadership(detail);
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
