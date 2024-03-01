package com.info.view.covid.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.covid.mapper.CovidMapper;
import com.info.view.covid.model.Covid;
import com.info.view.covid.service.ICovidService;

@Service("covidService")
public class CovidServiceImpl implements ICovidService {
	
	@Autowired
	private CovidMapper covidMapper;


	public void addCovid(Covid covid) {
		covidMapper.addCovid(covid);
	}

	public void updateCovid(Covid covid) {
		covidMapper.updateCovid(covid);
	}

	public void deteleCovid(String id) {
		covidMapper.deteleCovid(id);
	}

	public List<Covid> findAll() {
		return covidMapper.findAll();
	}

	public Covid findById(String id) {
		return covidMapper.findById(id);
	}

	public Covid login(String covidNo, String password) {
		List<Covid> list = covidMapper.findByCondition(" and covidNo='" + covidNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Covid)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Covid covid) {
		String sqlWhere = "";
		//查询标题
		if(covid.getCovidName() != null && !"".equals(covid.getCovidName())){
			sqlWhere += " and covidName like '%"+ covid.getCovidName() +"%'";
		}
		//查询内容
		if(covid.getCovidContext() != null && !"".equals(covid.getCovidContext())){
			sqlWhere += " and covidContext like '%"+ covid.getCovidContext() +"%'";
		}
		//查询措施
		if(covid.getCovidSc() != null && !"".equals(covid.getCovidSc())){
			sqlWhere += " and covidSc like '%"+ covid.getCovidSc() +"%'";
		}
		//查询地区
		if(covid.getCovidAddress() != null && !"".equals(covid.getCovidAddress())){
			sqlWhere += " and covidAddress like '%"+ covid.getCovidAddress() +"%'";
		}
		//查询等级
		if(covid.getCovidType() != null && !"".equals(covid.getCovidType())){
			sqlWhere += " and covidType like '%"+ covid.getCovidType() +"%'";
		}
		//查询主键
		if(covid.getResourceId() != null && !"".equals(covid.getResourceId())){
			sqlWhere += " and resourceId like '%"+ covid.getResourceId() +"%'";
		}
		//查询创建人ID
		if(covid.getCreatorId() != null && !"".equals(covid.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ covid.getCreatorId() +"%'";
		}
		//查询创建人
		if(covid.getCreatorName() != null && !"".equals(covid.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ covid.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(covid.getUpdaterId() != null && !"".equals(covid.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ covid.getUpdaterId() +"%'";
		}
		//查询修改人
		if(covid.getUpdaterName() != null && !"".equals(covid.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ covid.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = covidMapper.findByCondition(sqlWhere + limitSQL);
		int count = covidMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Covid> findByCondition(String sql){
		return covidMapper.findByCondition(sql);
	}
	
	public List<Covid> executeSQL(String sql){
		return covidMapper.executeSQL(sql);
	}
}
