package com.info.view.risk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.risk.mapper.RiskMapper;
import com.info.view.risk.model.Risk;
import com.info.view.risk.service.IRiskService;

@Service("riskService")
public class RiskServiceImpl implements IRiskService {
	
	@Autowired
	private RiskMapper riskMapper;


	public void addRisk(Risk risk) {
		riskMapper.addRisk(risk);
	}

	public void updateRisk(Risk risk) {
		riskMapper.updateRisk(risk);
	}

	public void deteleRisk(String id) {
		riskMapper.deteleRisk(id);
	}

	public List<Risk> findAll() {
		return riskMapper.findAll();
	}

	public Risk findById(String id) {
		return riskMapper.findById(id);
	}

	public Risk login(String riskNo, String password) {
		List<Risk> list = riskMapper.findByCondition(" and riskNo='" + riskNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Risk)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Risk risk) {
		String sqlWhere = "";
		//查询风险地区名称
		if(risk.getRiskName() != null && !"".equals(risk.getRiskName())){
			sqlWhere += " and riskName like '%"+ risk.getRiskName() +"%'";
		}
		//查询风险地区等级
		if(risk.getRiskType() != null && !"".equals(risk.getRiskType())){
			sqlWhere += " and riskType like '%"+ risk.getRiskType() +"%'";
		}
		//查询主键
		if(risk.getResourceId() != null && !"".equals(risk.getResourceId())){
			sqlWhere += " and resourceId like '%"+ risk.getResourceId() +"%'";
		}
		//查询创建人ID
		if(risk.getCreatorId() != null && !"".equals(risk.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ risk.getCreatorId() +"%'";
		}
		//查询创建人
		if(risk.getCreatorName() != null && !"".equals(risk.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ risk.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(risk.getUpdaterId() != null && !"".equals(risk.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ risk.getUpdaterId() +"%'";
		}
		//查询修改人
		if(risk.getUpdaterName() != null && !"".equals(risk.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ risk.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = riskMapper.findByCondition(sqlWhere + limitSQL);
		int count = riskMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Risk> findByCondition(String sql){
		return riskMapper.findByCondition(sql);
	}
	
	public List<Risk> executeSQL(String sql){
		return riskMapper.executeSQL(sql);
	}
}
