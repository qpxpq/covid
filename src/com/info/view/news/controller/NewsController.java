package com.info.view.news.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.info.view.news.model.News;
import com.info.view.news.service.INewsService;
import com.info.view.user.model.User;
import com.info.view.user.service.IUserService;

import net.sf.json.JSONObject;


@Controller
public class NewsController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private INewsService newsService;
	@Autowired
	private IUserService userService;
	
	
	
	
	
	@RequestMapping("findNews")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		News news = newsService.findById(id);
		modelMap.addAttribute("pageEntity", news);
		return "forward:/view/news/add_news.jsp";
	}
	@RequestMapping("updateNews")
	public String update(ModelMap modelMap,News news,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			news = initEntity.initUpdateInfo(news, request.getSession());
			newsService.updateNews(news);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findNews.do?id="+news.getResourceId();
		}
		return "redirect:/listNews.do";
	}
	@RequestMapping("delteNews")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		newsService.deteleNews(id);
		return "redirect:/listNews.do";
	}
	@RequestMapping("listNews")
	public String list(ModelMap modelMap,HttpServletRequest request,News news){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			news.setUserId(user.getResourceId());
		}
		
		pagerList = this.newsService.findPagerList(pagerList,news);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", news);
		return "forward:/view/news/list_news.jsp";
	}
	@RequestMapping("listSelectNews")
	public String listSelectNews(ModelMap modelMap,HttpServletRequest request,News news){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.newsService.findPagerList(pagerList, news);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  news);
		return "forward:/view/news/select_news.jsp";
	}

	
//    @RequestMapping("/addNews.do")
//    public String getProductInfo(HttpServletRequest request,HttpSession session) {
//        
//
//        return "info";
//    }
	
	
	@RequestMapping("addNews")
	public String add(String userId,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
	    String id = request.getParameter("id");
//	    System.out.println(id);
//	    System.out.println("------------------------------------------------------------------------------------------------------------------");
		try {
			User user = userService.findById(userId);
			//User user = userService.findById(userId);
//			System.out.println("userServiceeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
//			System.out.println(userService);//com.info.view.user.service.impl.UserServiceImpl@f4d25a3
//			System.out.println("userServiceeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
//			System.out.println(userService.findById(userId));//null
//			System.out.println("userService.findById(userId)))))))))))))))))))))))))))))))))))))))))))))))))))))))))))");
//			System.out.println(userService.findById(id));//null
//			System.out.println("userService.findById(id)))))))))))))))))))))))))))))))))))))))))))))))))))))))))))");
			
			
			
			String messageName="",messageContext="";
			//System.out.println("------------------------------------------------------------------------------------------------------------------");
			
			
			if (id !=null) {
				 System.out.println("判断id不为空");
				 
			 
			if("a".equals(id)){
				//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

				messageName = "正常通行";
				messageContext = "请正常通行。";
			}
			if("b".equals(id)){
				messageName = "落地核酸";
				messageContext = "请填报落地核酸申请。";
				//System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
			}
			if("c".equals(id)){
				messageName = "居家隔离";
				messageContext = "请填报居家隔离申请。";
				//System.out.println("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
			}
			if("d".equals(id)){
				messageName = "集中隔离";
				messageContext = "请填报集中隔离申请。";
				//System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
			}

			 }

			News news = new News();
			
			news.setMessageName(messageName);
			news.setMessageContext(messageContext);
			
			//System.out.println("user.getResourceId()zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
			//System.out.println(user.getResourceId());
			
			//System.out.println(user);user在这里是空
			
			//System.out.println("user.getUserName()NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
			//System.out.println(user.getUserName());
			//user.getUserName()这两个无法正确获取到id和name
			//user.getResourceId()
			
			news.setUserId(user.getResourceId());
			news.setUserName(user.getUserName());
			
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	news.setResourceId(UUID.randomUUID().toString().replace("-",""));
			news = initEntity.initAddInfo(news, request.getSession());
			newsService.addNews(news);
			
		} catch (Exception e) {
			System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			System.out.println(e);
			System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			e.printStackTrace();
		}
		return "redirect:/listNews.do";
	}
	
	
	@RequestMapping("exportNews")
	public String export(HttpServletRequest request,HttpServletResponse response,News news){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.newsService.findPagerList(pagerList,news);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"newsId","newsName","password","role","newsNo","cardNo","newsMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importNews")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,News news) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"newsName","password","role","newsNo","cardNo","newsMobile","sex"},
					News.class);
			//List<News> newsList = new ArrayList<News>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					News detail = (News)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//newsList.add(detail);
					this.newsService.addNews(detail);
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
