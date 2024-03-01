package com.info.view.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.info.view.user.model.User;
import com.info.view.user.service.IUserService;



//@Component
//public class TimerUtils {
//
//	@Autowired
//	private IUserService userservice;
//	
//	@Scheduled(cron = "0/60 * * * * ? ")//测试用 按照分钟执行60秒检测项目是否失效
//	//@Scheduled(cron = "0 0 6 * * ? ")//正式用按照天计算,每天6点执行.每天定时计算核算监测的时间
//	public void dealDate(){
// 		List<User> users = userservice.findAll();
//		for (User user : users) {
//			System.out.println("已接种".equals(user.getOneType()) + "-----------" + user.getOneType() + "------");
//			System.out.println("未接种".equals(user.getTwoType())+ "-----------" + user.getTwoType() + "------");
//			System.out.println("已接种".equals(user.getOneType()) && "未接种".equals(user.getTwoType()));
//			if("已结种".equals(user.getOneType()) && "未接种".equals(user.getTwoType())){
//				user.setOneTime(user.getOneTime() -1);
//				userservice.updateUser(user);
//			}
//			if("已结种".equals(user.getTwoType()) && "未接种".equals(user.getThreeType())){
//				user.setTwoTime(user.getTwoTime() -1);
//				userservice.updateUser(user);
//			}
//		}
//	}
//	
//}
