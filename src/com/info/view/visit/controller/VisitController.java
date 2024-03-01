package com.info.view.visit.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.info.view.user.model.User;
import com.info.view.visit.model.Visit;
import com.info.view.visit.model.VisitReport;
import com.info.view.visit.service.IVisitService;

import net.sf.json.JSONObject;


@Controller
public class VisitController extends BaseController{@Autowired private ILogService logService;
	
	@Autowired
	private IVisitService visitService;
	
	
	@RequestMapping("visitReport")
	public void visitReport(ModelMap modelMap,String type,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		List<VisitReport> visits = new ArrayList<VisitReport>();
		if("day".equals(type)){
			visits.add(addForm(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " 00:00:00", new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " 23:59:59"));
		} else if("mouth".equals(type)){
			int dd = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
			String now = new SimpleDateFormat("yyyy-MM").format(new Date());
			for (int i=1;i<=dd;i++) {
				if(i < 10){
					visits.add(addForm(now + "-0" + i, now + "-0" + i +" 00:00:00", now + "-0" + i +" 23:59:59"));
				} else {
					visits.add(addForm(now + "-" + i,  now + "-" + i +" 00:00:00", now + "-" + i +" 23:59:59"));
				}
			}
		} else if("year".equals(type)){
			for (int i=1;i<=12;i++) {
				String now = new SimpleDateFormat("yyyy").format(new Date());
				if(i < 10){
					visits.add(addForm(i +"月" ,  now + "-0" + i + "-01 00:00:00", now + "-0" + i + "-31 23:59:59"));
				} else {
					visits.add(addForm(i  +"月" ,  now + "-0" + i + "-01 00:00:00", now + "-0" + i + "-31 23:59:59"));
				}
			}
		}
		String name ="",value="";
		for (VisitReport en : visits) {
			name = name + ",'" + en.getName() + "'";
			value = value + "," + en.getValue() + "";
		}
		
		//--柱状图
		String option = "{ "+
				"	    color: ['#3398DB'], "+
				"	    tooltip: { "+
				"	        trigger: 'axis', "+
				"	        axisPointer: {  "+           // 坐标轴指示器，坐标轴触发有效
				"	            type: 'shadow'  "+       // 默认为直线，可选为：'line' | 'shadow'
				"	        } "+
				"	    }, "+
				"	    xAxis: [ "+
				"	        { "+
				"	            type: 'category', "+
				"	            data: ["+ name.substring(1) +"], "+
				"	            axisTick: { "+
				"	                alignWithLabel: true "+
				"	            } "+
				"	        } "+
				"	    ], "+
				"	    yAxis: [ "+
				"	        { "+
				"	            type: 'value' "+
				"	        } "+
				"	    ], "+
				"	    series: [ "+
				"	        { "+
				"	            name: '访客人数总计', "+
				"	            type: 'bar', "+
				"	            barWidth: '60%', "+
				"	            data: ["+ value.substring(1) +"] "+
				"	        } "+
				"	    ] "+
				"	} ";
				System.out.println(option);
				try {
					response.getWriter().write(option);
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
	private VisitReport addForm(String nowDate,String startDate,String endDate){
		VisitReport formReport = new VisitReport();
		List<Visit> orderForms = visitService.findByCondition(" and createTime >='"+ startDate +"' and createTime <= '"+ endDate +"'");
		if(orderForms != null && orderForms.size() > 0){
			Integer dd = 0;
			for (Visit userOrderForm : orderForms) {
				dd += Integer.parseInt(userOrderForm.getVisitRs());
			}
			formReport.setName(nowDate);
			formReport.setValue(dd);
		} else {
			formReport.setName(nowDate);
			formReport.setValue(0);
		}
		return formReport;
	}
	
	@RequestMapping("findVisit")
	public String find(ModelMap modelMap,String id,HttpServletRequest request){logService.addLogInfo(request);
		Visit visit = visitService.findById(id);
		modelMap.addAttribute("pageEntity", visit);
		return "forward:/view/visit/add_visit.jsp";
	}
	@RequestMapping("updateVisit")
	public String update(ModelMap modelMap,Visit visit,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		//初始化新增的数据
		InitEntity initEntity = new InitEntity();
		try {
			visit = initEntity.initUpdateInfo(visit, request.getSession());
			visitService.updateVisit(visit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String role = (String)request.getSession().getAttribute("role");
		if(role == null || "".equals(role)){
			return "redirect:/login.jsp";
		}else if(!"admin".equals(role)){
			return "redirect:/findVisit.do?id="+visit.getResourceId();
		}
		return "redirect:/listVisit.do";
	}
	@RequestMapping("delteVisit")
	public String delte(String id,HttpServletRequest request){logService.addLogInfo(request);
		visitService.deteleVisit(id);
		return "redirect:/listVisit.do";
	}
	@RequestMapping("listVisit")
	public String list(ModelMap modelMap,HttpServletRequest request,Visit visit){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		
		String role = (String)request.getSession().getAttribute("role");
		if("user".equals(role)){
			User user = (User)request.getSession().getAttribute("userSession");
			visit.setCreatorId(user.getResourceId());
		}
		
		pagerList = this.visitService.findPagerList(pagerList,visit);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList", visit);
		return "forward:/view/visit/list_visit.jsp";
	}
	@RequestMapping("listSelectVisit")
	public String listSelectVisit(ModelMap modelMap,HttpServletRequest request,Visit visit){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(StaticValue.pageSize);
		pagerList = this.visitService.findPagerList(pagerList, visit);
		modelMap.addAttribute("pagerList", pagerList);
		modelMap.addAttribute("searchList",  visit);
		return "forward:/view/visit/select_visit.jsp";
	}
	
	@RequestMapping("addVisit")
	public String add(Visit visit,HttpServletRequest request,HttpServletResponse response){logService.addLogInfo(request);
		try {
			//初始化新增的数据
			InitEntity initEntity = new InitEntity();
	    	visit.setResourceId(UUID.randomUUID().toString().replace("-",""));
			visit = initEntity.initAddInfo(visit, request.getSession());
			visitService.addVisit(visit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listVisit.do";
	}
	
	
	@RequestMapping("exportVisit")
	public String export(HttpServletRequest request,HttpServletResponse response,Visit visit){
		logService.addLogInfo(request);PagerList pagerList = new PagerList(request);
		pagerList.setPageSize(999999);
		pagerList.setPageIndex(1);
		PagerList pagelet = this.visitService.findPagerList(pagerList,visit);
		ObjectToExcel objectToExcl = ObjectToExcel.getInstance(pagelet, null, new String[]{"主键","学生名称","密码" ,"权限","登录名","身份证","电话","性别"},
				new String[]{"visitId","visitName","password","role","visitNo","cardNo","visitMobile","sex"}, null, null);
		String fileId = objectToExcl.convertToExcel();
		String root = StaticValue.ATTACH_PATH;
		DownFileHelper.downFile(response,root,fileId);
		return null;
	}
	
	@RequestMapping("importVisit")
	public String importExcl(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response,Visit visit) throws Exception{
		JSONObject json = new JSONObject();
		try{
			List<?> impList = ExcelToObject.getInstance().parseExcel(file.getInputStream(),
					new String[]{"visitName","password","role","visitNo","cardNo","visitMobile","sex"},
					Visit.class);
			//List<Visit> visitList = new ArrayList<Visit>();
			if(impList != null && impList.size() > 0){
				//自己在设置自己需要的值
				for(Object obj:impList){
					Visit detail = (Visit)obj;
					detail.setResourceId(UUID.randomUUID().toString().replace("-",""));
					//visitList.add(detail);
					this.visitService.addVisit(detail);
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
