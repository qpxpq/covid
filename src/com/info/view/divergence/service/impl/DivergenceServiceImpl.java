package com.info.view.divergence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.divergence.mapper.DivergenceMapper;
import com.info.view.divergence.model.Divergence;
import com.info.view.divergence.service.IDivergenceService;

@Service("divergenceService")
public class DivergenceServiceImpl implements IDivergenceService {
	
	@Autowired
	private DivergenceMapper divergenceMapper;


	public void addDivergence(Divergence divergence) {
		divergenceMapper.addDivergence(divergence);
	}

	public void updateDivergence(Divergence divergence) {
		divergenceMapper.updateDivergence(divergence);
	}

	public void deteleDivergence(String id) {
		divergenceMapper.deteleDivergence(id);
	}

	public List<Divergence> findAll() {
		return divergenceMapper.findAll();
	}

	public Divergence findById(String id) {
		return divergenceMapper.findById(id);
	}

	public Divergence login(String divergenceNo, String password) {
		List<Divergence> list = divergenceMapper.findByCondition(" and divergenceNo='" + divergenceNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Divergence)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Divergence divergence) {
		String sqlWhere = "";
		//查询登记标题
		if(divergence.getDivergenceName() != null && !"".equals(divergence.getDivergenceName())){
			sqlWhere += " and divergenceName like '%"+ divergence.getDivergenceName() +"%'";
		}
		//查询登记内容
		if(divergence.getDivergenceContext() != null && !"".equals(divergence.getDivergenceContext())){
			sqlWhere += " and divergenceContext like '%"+ divergence.getDivergenceContext() +"%'";
		}
		//查询登记类型
		if(divergence.getDivergenceType() != null && !"".equals(divergence.getDivergenceType())){
			sqlWhere += " and divergenceType like '%"+ divergence.getDivergenceType() +"%'";
		}
		//查询主键
		if(divergence.getResourceId() != null && !"".equals(divergence.getResourceId())){
			sqlWhere += " and resourceId like '%"+ divergence.getResourceId() +"%'";
		}
		//查询创建人ID
		if(divergence.getCreatorId() != null && !"".equals(divergence.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ divergence.getCreatorId() +"%'";
		}
		//查询创建人
		if(divergence.getCreatorName() != null && !"".equals(divergence.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ divergence.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(divergence.getUpdaterId() != null && !"".equals(divergence.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ divergence.getUpdaterId() +"%'";
		}
		//查询修改人
		if(divergence.getUpdaterName() != null && !"".equals(divergence.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ divergence.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = divergenceMapper.findByCondition(sqlWhere + limitSQL);
		int count = divergenceMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Divergence> findByCondition(String sql){
		return divergenceMapper.findByCondition(sql);
	}
	
	public List<Divergence> executeSQL(String sql){
		return divergenceMapper.executeSQL(sql);
	}
}
