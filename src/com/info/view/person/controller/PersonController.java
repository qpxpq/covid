package com.info.view.person.controller;

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
import com.info.view.person.model.Person;
import com.info.view.person.service.IPersonService;

@Controller
public class PersonController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IPersonService personService;
	@RequestMapping("findPerson")
	public String find(ModelMap modelMap,String id){
		Person person = personService.findById(id);
		modelMap.addAttribute("person", person);
		return "forward:/view/person/add_person.jsp";
	}
	@RequestMapping("updatePerson")
	public String update(ModelMap modelMap,Person person,HttpSession session){
		personService.updatePerson(person);
		String role = (String)session.getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findPerson.do?id="+person.getPersonId();
		}
		return "redirect:/listPerson.do";
	}
	@RequestMapping("deltePerson")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		personService.detelePerson(id);
		return "redirect:/listPerson.do";
	}
	@RequestMapping("listPerson")
	public String list(ModelMap modelMap,HttpServletRequest request,Person person){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.personService.findPagerList(pagerList,person);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", person);
		return "forward:/view/person/list_person.jsp";
	}
	@RequestMapping("addPerson")
	public String add(Person person){
		person.setPersonId(UUID.randomUUID().toString().replace("-",""));
		personService.addPerson(person);
		return "redirect:/listPerson.do";
	}
	@RequestMapping("exportPerson")
	public String export(HttpServletRequest request,HttpServletResponse response,Person person){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.personService.findPagerList(pagerList,person);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"personId","personName","password","role","personNo","cardNo","personMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importPerson")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Person person) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"personName","password","role","personNo","cardNo","personMobile","sex"},
					Person.class);
			//List<Person> personList = new ArrayList<Person>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Person detail = (Person)obj;
					//personList.add(detail);
					this.personService.addPerson(detail);
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
