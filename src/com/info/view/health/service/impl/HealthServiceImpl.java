package com.info.view.health.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.health.mapper.HealthMapper;
import com.info.view.health.model.Health;
import com.info.view.health.service.IHealthService;

@Service("healthService")
public class HealthServiceImpl implements IHealthService {
	
	@Autowired
	private HealthMapper healthMapper;


	public void addHealth(Health health) {
		healthMapper.addHealth(health);
	}

	public void updateHealth(Health health) {
		healthMapper.updateHealth(health);
	}

	public void deteleHealth(String id) {
		healthMapper.deteleHealth(id);
	}

	public List<Health> findAll() {
		return healthMapper.findAll();
	}

	public Health findById(String id) {
		return healthMapper.findById(id);
	}

	public Health login(String healthNo, String password) {
		List<Health> list = healthMapper.findByCondition(" and healthNo='" + healthNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Health)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Health health) {
		String sqlWhere = "";
		//查询健康状态
		if(health.getHealthType() != null && !"".equals(health.getHealthType())){
			sqlWhere += " and healthType like '%"+ health.getHealthType() +"%'";
		}
		//查询体温
		if(health.getTw() != null && !"".equals(health.getTw())){
			sqlWhere += " and tw like '%"+ health.getTw() +"%'";
		}
		//查询描述
		if(health.getHealthContext() != null && !"".equals(health.getHealthContext())){
			sqlWhere += " and healthContext like '%"+ health.getHealthContext() +"%'";
		}
		//查询主键
		if(health.getResourceId() != null && !"".equals(health.getResourceId())){
			sqlWhere += " and resourceId like '%"+ health.getResourceId() +"%'";
		}
		//查询创建人ID
		if(health.getCreatorId() != null && !"".equals(health.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ health.getCreatorId() +"%'";
		}
		//查询创建人
		if(health.getCreatorName() != null && !"".equals(health.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ health.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(health.getUpdaterId() != null && !"".equals(health.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ health.getUpdaterId() +"%'";
		}
		//查询修改人
		if(health.getUpdaterName() != null && !"".equals(health.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ health.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = healthMapper.findByCondition(sqlWhere + limitSQL);
		int count = healthMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Health> findByCondition(String sql){
		return healthMapper.findByCondition(sql);
	}
	
	public List<Health> executeSQL(String sql){
		return healthMapper.executeSQL(sql);
	}
}
