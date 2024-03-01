package com.info.view.leadership.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.leadership.mapper.LeadershipMapper;
import com.info.view.leadership.model.Leadership;
import com.info.view.leadership.service.ILeadershipService;

@Service("leadershipService")
public class LeadershipServiceImpl implements ILeadershipService {
	
	@Autowired
	private LeadershipMapper leadershipMapper;


	public void addLeadership(Leadership leadership) {
		leadershipMapper.addLeadership(leadership);
	}

	public void updateLeadership(Leadership leadership) {
		leadershipMapper.updateLeadership(leadership);
	}

	public void deteleLeadership(String id) {
		leadershipMapper.deteleLeadership(id);
	}

	public List<Leadership> findAll() {
		return leadershipMapper.findAll();
	}

	public Leadership findById(String id) {
		return leadershipMapper.findById(id);
	}

	public Leadership login(String leadershipNo, String password) {
		List<Leadership> list = leadershipMapper.findByCondition(" and leadershipNo='" + leadershipNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Leadership)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Leadership leadership) {
		String sqlWhere = "";
		//查询健康状态
		if(leadership.getHealthStatus() != null && !"".equals(leadership.getHealthStatus())){
			sqlWhere += " and healthStatus like '%"+ leadership.getHealthStatus() +"%'";
		}
		//查询体温
		if(leadership.getTw() != null && !"".equals(leadership.getTw())){
			sqlWhere += " and tw like '%"+ leadership.getTw() +"%'";
		}
		//查询描述
		if(leadership.getHealthContext() != null && !"".equals(leadership.getHealthContext())){
			sqlWhere += " and healthContext like '%"+ leadership.getHealthContext() +"%'";
		}
		//查询风险地区
		if(leadership.getFxdq() != null && !"".equals(leadership.getFxdq())){
			sqlWhere += " and fxdq like '%"+ leadership.getFxdq() +"%'";
		}
		//查询交通方式
		if(leadership.getJtfs() != null && !"".equals(leadership.getJtfs())){
			sqlWhere += " and jtfs like '%"+ leadership.getJtfs() +"%'";
		}
		//查询其他信息
		if(leadership.getInfo() != null && !"".equals(leadership.getInfo())){
			sqlWhere += " and info like '%"+ leadership.getInfo() +"%'";
		}
		//查询用户Id
		if(leadership.getUserId() != null && !"".equals(leadership.getUserId())){
			sqlWhere += " and userId like '%"+ leadership.getUserId() +"%'";
		}
		//查询用户名称
		if(leadership.getUserName() != null && !"".equals(leadership.getUserName())){
			sqlWhere += " and userName like '%"+ leadership.getUserName() +"%'";
		}
		//查询审核人
		if(leadership.getAuthorId() != null && !"".equals(leadership.getAuthorId())){
			sqlWhere += " and authorId like '%"+ leadership.getAuthorId() +"%'";
		}
		//查询审核人
		if(leadership.getAuthorName() != null && !"".equals(leadership.getAuthorName())){
			sqlWhere += " and authorName like '%"+ leadership.getAuthorName() +"%'";
		}
		//查询回复内容
		if(leadership.getAuthorContext() != null && !"".equals(leadership.getAuthorContext())){
			sqlWhere += " and authorContext like '%"+ leadership.getAuthorContext() +"%'";
		}
		//查询状态
		if(leadership.getHealthType() != null && !"".equals(leadership.getHealthType())){
			sqlWhere += " and healthType like '%"+ leadership.getHealthType() +"%'";
		}
		//查询主键
		if(leadership.getResourceId() != null && !"".equals(leadership.getResourceId())){
			sqlWhere += " and resourceId like '%"+ leadership.getResourceId() +"%'";
		}
		//查询创建人ID
		if(leadership.getCreatorId() != null && !"".equals(leadership.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ leadership.getCreatorId() +"%'";
		}
		//查询创建人
		if(leadership.getCreatorName() != null && !"".equals(leadership.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ leadership.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(leadership.getUpdaterId() != null && !"".equals(leadership.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ leadership.getUpdaterId() +"%'";
		}
		//查询修改人
		if(leadership.getUpdaterName() != null && !"".equals(leadership.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ leadership.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = leadershipMapper.findByCondition(sqlWhere + limitSQL);
		int count = leadershipMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Leadership> findByCondition(String sql){
		return leadershipMapper.findByCondition(sql);
	}
	
	public List<Leadership> executeSQL(String sql){
		return leadershipMapper.executeSQL(sql);
	}
}
