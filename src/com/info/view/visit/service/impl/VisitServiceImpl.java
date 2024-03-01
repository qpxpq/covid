package com.info.view.visit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.visit.mapper.VisitMapper;
import com.info.view.visit.model.Visit;
import com.info.view.visit.service.IVisitService;

@Service("visitService")
public class VisitServiceImpl implements IVisitService {
	
	@Autowired
	private VisitMapper visitMapper;


	public void addVisit(Visit visit) {
		visitMapper.addVisit(visit);
	}

	public void updateVisit(Visit visit) {
		visitMapper.updateVisit(visit);
	}

	public void deteleVisit(String id) {
		visitMapper.deteleVisit(id);
	}

	public List<Visit> findAll() {
		return visitMapper.findAll();
	}

	public Visit findById(String id) {
		return visitMapper.findById(id);
	}

	public Visit login(String visitNo, String password) {
		List<Visit> list = visitMapper.findByCondition(" and visitNo='" + visitNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Visit)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Visit visit) {
		String sqlWhere = "";
		//查询来访人员
		if(visit.getVisitName() != null && !"".equals(visit.getVisitName())){
			sqlWhere += " and visitName like '%"+ visit.getVisitName() +"%'";
		}
		//查询事由
		if(visit.getVisitSy() != null && !"".equals(visit.getVisitSy())){
			sqlWhere += " and visitSy like '%"+ visit.getVisitSy() +"%'";
		}
		//查询人数
		if(visit.getVisitRs() != null && !"".equals(visit.getVisitRs())){
			sqlWhere += " and visitRs like '%"+ visit.getVisitRs() +"%'";
		}
		//查询主键
		if(visit.getResourceId() != null && !"".equals(visit.getResourceId())){
			sqlWhere += " and resourceId like '%"+ visit.getResourceId() +"%'";
		}
		//查询创建人ID
		if(visit.getCreatorId() != null && !"".equals(visit.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ visit.getCreatorId() +"%'";
		}
		//查询登记人
		if(visit.getCreatorName() != null && !"".equals(visit.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ visit.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(visit.getUpdaterId() != null && !"".equals(visit.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ visit.getUpdaterId() +"%'";
		}
		//查询修改人
		if(visit.getUpdaterName() != null && !"".equals(visit.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ visit.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = visitMapper.findByCondition(sqlWhere + limitSQL);
		int count = visitMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Visit> findByCondition(String sql){
		return visitMapper.findByCondition(sql);
	}
	
	public List<Visit> executeSQL(String sql){
		return visitMapper.executeSQL(sql);
	}
}
