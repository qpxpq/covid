package com.info.view.note.controller;

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
import com.info.view.note.model.Note;
import com.info.view.note.service.INoteService;
import com.info.view.user.model.User;
import com.info.view.work.model.Work;

import net.sf.json.JSONObject;


@Controller
public class NoteController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private INoteService noteService;
	
	
	
	
	
	
	@RequestMapping("findNote")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Note note = noteService.findById(id);
		modelMap.addAttribute("pageEntity", note);
		return "forward:/view/note/add_note.jsp";
	}
	@RequestMapping("updateNote")
	public String update(ModelMap modelMap,Note note,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			note = initEntity.initUpdateInfo(note, request.getSession());
			noteService.updateNote(note);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findNote.do?id="+note.getResourceId();
		}
		return "redirect:/listNote.do";
	}
	@RequestMapping("delteNote")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		noteService.deteleNote(id);
		return "redirect:/listNote.do";
	}
	@RequestMapping("listNote")
	public String list(ModelMap modelMap,HttpServletRequest request,Note note){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
//		String role = (String)request.getSession().getAttribute("role");
//		if("user".equals(role)){
//			User user = (User)request.getSession().getAttribute("userSession");
//			note.setCreatorId(user.getResourceId());
//		}
//		if("work".equals(role)){
//			Work work = (Work)request.getSession().getAttribute("workSession");
//			note.setCreatorId(work.getResourceId());
//		}
		
		pagerList = this.noteService.findPagerList(pagerList,note);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", note);
		return "forward:/view/note/list_note.jsp";
	}
	@RequestMapping("listSelectNote")
	public String listSelectNote(ModelMap modelMap,HttpServletRequest request,Note note){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.noteService.findPagerList(pagerList, note);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  note);
		return "forward:/view/note/select_note.jsp";
	}
	
	@RequestMapping("addNote")
	public String add(Note note,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	note.setResourceId(UUID.randomUUID().toString().replace("-",""));
			note = initEntity.initAddInfo(note, request.getSession());
			noteService.addNote(note);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listNote.do";
	}
	
	
	@RequestMapping("exportNote")
	public String export(HttpServletRequest request,HttpServletResponse response,Note note){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.noteService.findPagerList(pagerList,note);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"noteId","noteName","password","role","noteNo","cardNo","noteMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importNote")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Note note) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"noteName","password","role","noteNo","cardNo","noteMobile","sex"},
					Note.class);
			//List<Note> noteList = new ArrayList<Note>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Note detail = (Note)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//noteList.add(detail);
					this.noteService.addNote(detail);
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
