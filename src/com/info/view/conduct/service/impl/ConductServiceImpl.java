package com.info.view.conduct.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.conduct.mapper.ConductMapper;
import com.info.view.conduct.model.Conduct;
import com.info.view.conduct.service.IConductService;

@Service("conductService")
public class ConductServiceImpl implements IConductService {
	
	@Autowired
	private ConductMapper conductMapper;


	public void addConduct(Conduct conduct) {
		conductMapper.addConduct(conduct);
	}

	public void updateConduct(Conduct conduct) {
		conductMapper.updateConduct(conduct);
	}

	public void deteleConduct(String id) {
		conductMapper.deteleConduct(id);
	}

	public List<Conduct> findAll() {
		return conductMapper.findAll();
	}

	public Conduct findById(String id) {
		return conductMapper.findById(id);
	}

	public Conduct login(String conductNo, String password) {
		List<Conduct> list = conductMapper.findByCondition(" and conductNo='" + conductNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Conduct)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Conduct conduct) {
		String sqlWhere = "";
		//查询宣传名称
		if(conduct.getConductName() != null && !"".equals(conduct.getConductName())){
			sqlWhere += " and conductName like '%"+ conduct.getConductName() +"%'";
		}
		//查询宣传内容
		if(conduct.getConductContext() != null && !"".equals(conduct.getConductContext())){
			sqlWhere += " and conductContext like '%"+ conduct.getConductContext() +"%'";
		}
		//查询主键
		if(conduct.getResourceId() != null && !"".equals(conduct.getResourceId())){
			sqlWhere += " and resourceId like '%"+ conduct.getResourceId() +"%'";
		}
		//查询创建人ID
		if(conduct.getCreatorId() != null && !"".equals(conduct.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ conduct.getCreatorId() +"%'";
		}
		//查询创建人
		if(conduct.getCreatorName() != null && !"".equals(conduct.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ conduct.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(conduct.getUpdaterId() != null && !"".equals(conduct.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ conduct.getUpdaterId() +"%'";
		}
		//查询修改人
		if(conduct.getUpdaterName() != null && !"".equals(conduct.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ conduct.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = conductMapper.findByCondition(sqlWhere + limitSQL);
		int count = conductMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Conduct> findByCondition(String sql){
		return conductMapper.findByCondition(sql);
	}
	
	public List<Conduct> executeSQL(String sql){
		return conductMapper.executeSQL(sql);
	}
}
