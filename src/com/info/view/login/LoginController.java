package com.info.view.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.info.common.staticvalue.StaticValue;
import com.info.view.person.model.Person;
import com.info.view.person.service.IPersonService;
import com.info.view.user.model.User;
import com.info.view.user.service.IUserService;
import com.info.view.work.model.Work;
import com.info.view.work.service.IWorkService;

@Controller
public class LoginController {
	
	@Autowired
	private IPersonService personService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IWorkService workService;
	
	
	@RequestMapping("login")
	public String login(String username,String password,String operate,HttpSession session,ModelMap modelMap){
		if("super".equals(operate)){
			if(username.equals(StaticValue.USERNAME) && password.endsWith(StaticValue.PASSWORD)){
				session.setAttribute("systemName", "超级管理员");
				session.setAttribute("role", "super");
				return "redirect:/index.jsp";
			}
		}
		
		if("admin".equals(operate)){
			Person person = personService.login(username, password);
			if(person != null){
				session.setAttribute("personSession", person);
				session.setAttribute("systemName", person.getPersonName());
				session.setAttribute("role", "admin");
				return "redirect:/index.jsp";
			}
		}
		if("user".equals(operate)){
			User user = userService.login(username, password);
			if(user != null){
				session.setAttribute("userSession", user);
				session.setAttribute("systemName", user.getUserName());
				session.setAttribute("role", "user");
				return "redirect:/index.jsp";
			}
		}
		if("work".equals(operate)){
			Work work = workService.login(username, password);
			if(work != null){
				session.setAttribute("workSession", work);
				session.setAttribute("systemName", work.getWorkName());
				session.setAttribute("role", "work");
				return "redirect:/index.jsp";
			}
		}
		
		modelMap.addAttribute("msg", "登陆失败！请重新登陆！");
		return "forward:/login.jsp";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.removeAttribute("role");
		session.removeAttribute("systemName");
		session.removeAttribute("personSession");
		
		session.removeAttribute("Session");
		session.removeAttribute("userSession");
		session.removeAttribute("workSession");
		return "redirect:/login.jsp";
	}
	
	
	@RequestMapping("downloadModel")
	public String downloadModel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String fileName = request.getParameter("file");
		String folder = request.getParameter("folder");
		try{
			fileName = URLDecoder.decode(fileName, "UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		String realPath = request.getSession().getServletContext().getRealPath("/")+"view/"+folder+"/"+fileName;
		File file = new File(realPath);
		InputStream inStream = null;
		if(file.exists()){
			// 设置输出的格式
			response.reset();
			response.setContentType("bin");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(), "ISO-8859-1") + "\"");
			// 读到流中
			inStream = new FileInputStream(file);// 文件的存放路径
			// 循环取出流中的数据
			byte[] b = new byte[100];
			int len;
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
			return null;
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("<script>alert('当前下载文档不存在');window.open('about:blank','_self').close()</script>");
		return null;
	}
}
