package com.info.view.message.controller;

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
import com.info.view.message.model.Message;
import com.info.view.message.service.IMessageService;
import com.info.view.user.model.User;

import net.sf.json.JSONObject;


@Controller
public class MessageController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IMessageService messageService;
	
	
	
	
	
	
	@RequestMapping("findMessage")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Message message = messageService.findById(id);
		modelMap.addAttribute("pageEntity", message);
		
		String type = request.getParameter("type");
		if("author".equals(type)){
			return "forward:/view/message/author_message.jsp";
		}
		
		return "forward:/view/message/add_message.jsp";
	}
	@RequestMapping("updateMessage")
	public String update(ModelMap modelMap,Message message,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			message = initEntity.initUpdateInfo(message, request.getSession());
			messageService.updateMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listMessage.do";
	}
	@RequestMapping("delteMessage")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		messageService.deteleMessage(id);
		return "redirect:/listMessage.do";
	}
	@RequestMapping("listMessage")
	public String list(ModelMap modelMap,HttpServletRequest request,Message message){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			message.setUserId(user.getResourceId());
		}
		pagerList = this.messageService.findPagerList(pagerList,message);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", message);
		return "forward:/view/message/list_message.jsp";
	}
	@RequestMapping("listSelectMessage")
	public String listSelectMessage(ModelMap modelMap,HttpServletRequest request,Message message){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			message.setUserId(user.getResourceId());
		}
		
		pagerList = this.messageService.findPagerList(pagerList, message);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  message);
		return "forward:/view/message/select_message.jsp";
	}
	
	@RequestMapping("addMessage")
	public String add(Message message,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	message.setResourceId(UUID.randomUUID().toString().replace("-",""));
			message = initEntity.initAddInfo(message, request.getSession());
			messageService.addMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listMessage.do";
	}
	
	
	@RequestMapping("exportMessage")
	public String export(HttpServletRequest request,HttpServletResponse response,Message message){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.messageService.findPagerList(pagerList,message);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"messageId","messageName","password","role","messageNo","cardNo","messageMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importMessage")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Message message) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"messageName","password","role","messageNo","cardNo","messageMobile","sex"},
					Message.class);
			//List<Message> messageList = new ArrayList<Message>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Message detail = (Message)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//messageList.add(detail);
					this.messageService.addMessage(detail);
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
