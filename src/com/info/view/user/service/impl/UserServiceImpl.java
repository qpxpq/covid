package com.info.view.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.user.mapper.UserMapper;
import com.info.view.user.model.User;
import com.info.view.user.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;


	public void addUser(User user) {
		userMapper.addUser(user);
	}

	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	public void deteleUser(String id) {
		userMapper.deteleUser(id);
	}

	public List<User> findAll() {
		return userMapper.findAll();
	}

	public User findById(String id) {
		return userMapper.findById(id);
	}

	public User login(String userNo, String password) {
		List<User> list = userMapper.findByCondition(" and userNo='" + userNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (User)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,User user) {
		String sqlWhere = "";
		//查询账号
		if(user.getUserNo() != null && !"".equals(user.getUserNo())){
			sqlWhere += " and userNo like '%"+ user.getUserNo() +"%'";
		}
		//查询密码
		if(user.getPassword() != null && !"".equals(user.getPassword())){
			sqlWhere += " and password like '%"+ user.getPassword() +"%'";
		}
		//查询姓名
		if(user.getUserName() != null && !"".equals(user.getUserName())){
			sqlWhere += " and userName like '%"+ user.getUserName() +"%'";
		}
		//查询性别
		if(user.getSex() != null && !"".equals(user.getSex())){
			sqlWhere += " and sex like '%"+ user.getSex() +"%'";
		}
		//查询电话
		if(user.getMobile() != null && !"".equals(user.getMobile())){
			sqlWhere += " and mobile like '%"+ user.getMobile() +"%'";
		}
		//查询地址
		if(user.getAddress() != null && !"".equals(user.getAddress())){
			sqlWhere += " and address like '%"+ user.getAddress() +"%'";
		}
		//查询居民状态
		if(user.getUserType() != null && !"".equals(user.getUserType())){
			sqlWhere += " and userType like '%"+ user.getUserType() +"%'";
		}
		//查询一状态
		if(user.getOneType() != null && !"".equals(user.getOneType())){
			sqlWhere += " and oneType like '%"+ user.getOneType() +"%'";
		}
		//查询二状态
		if(user.getTwoType() != null && !"".equals(user.getTwoType())){
			sqlWhere += " and twoType like '%"+ user.getTwoType() +"%'";
		}
		//查询三状态
		if(user.getThreeType() != null && !"".equals(user.getThreeType())){
			sqlWhere += " and threeType like '%"+ user.getThreeType() +"%'";
		}
		//查询主键
		if(user.getResourceId() != null && !"".equals(user.getResourceId())){
			sqlWhere += " and resourceId like '%"+ user.getResourceId() +"%'";
		}
		//查询创建人ID
		if(user.getCreatorId() != null && !"".equals(user.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ user.getCreatorId() +"%'";
		}
		//查询创建人
		if(user.getCreatorName() != null && !"".equals(user.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ user.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(user.getUpdaterId() != null && !"".equals(user.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ user.getUpdaterId() +"%'";
		}
		//查询修改人
		if(user.getUpdaterName() != null && !"".equals(user.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ user.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = userMapper.findByCondition(sqlWhere + limitSQL);
		int count = userMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);

		return pagerList;

	}
	
	public List<User> findByCondition(String sql){
		return userMapper.findByCondition(sql);
	}
	
	public List<User> executeSQL(String sql){
		return userMapper.executeSQL(sql);
	}
}
