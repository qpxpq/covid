package com.info.view.accusation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.accusation.mapper.AccusationMapper;
import com.info.view.accusation.model.Accusation;
import com.info.view.accusation.service.IAccusationService;

@Service("accusationService")
public class AccusationServiceImpl implements IAccusationService {
	
	@Autowired
	private AccusationMapper accusationMapper;


	public void addAccusation(Accusation accusation) {
		accusationMapper.addAccusation(accusation);
	}

	public void updateAccusation(Accusation accusation) {
		accusationMapper.updateAccusation(accusation);
	}

	public void deteleAccusation(String id) {
		accusationMapper.deteleAccusation(id);
	}

	public List<Accusation> findAll() {
		return accusationMapper.findAll();
	}

	public Accusation findById(String id) {
		return accusationMapper.findById(id);
	}

	public Accusation login(String accusationNo, String password) {
		List<Accusation> list = accusationMapper.findByCondition(" and accusationNo='" + accusationNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Accusation)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Accusation accusation) {
		String sqlWhere = "";
		//查询申请标题
		if(accusation.getAccusationName() != null && !"".equals(accusation.getAccusationName())){
			sqlWhere += " and accusationName like '%"+ accusation.getAccusationName() +"%'";
		}
		//查询申请内容
		if(accusation.getAccusationContext() != null && !"".equals(accusation.getAccusationContext())){
			sqlWhere += " and accusationContext like '%"+ accusation.getAccusationContext() +"%'";
		}
		//查询用户Id
		if(accusation.getUserId() != null && !"".equals(accusation.getUserId())){
			sqlWhere += " and userId like '%"+ accusation.getUserId() +"%'";
		}
		//查询申请人
		if(accusation.getUserName() != null && !"".equals(accusation.getUserName())){
			sqlWhere += " and userName like '%"+ accusation.getUserName() +"%'";
		}
		//查询审核人
		if(accusation.getAuthorId() != null && !"".equals(accusation.getAuthorId())){
			sqlWhere += " and authorId like '%"+ accusation.getAuthorId() +"%'";
		}
		//查询审核人
		if(accusation.getAuthorName() != null && !"".equals(accusation.getAuthorName())){
			sqlWhere += " and authorName like '%"+ accusation.getAuthorName() +"%'";
		}
		//查询回复内容
		if(accusation.getAuthorContext() != null && !"".equals(accusation.getAuthorContext())){
			sqlWhere += " and authorContext like '%"+ accusation.getAuthorContext() +"%'";
		}
		//查询状态
		if(accusation.getAccusationType() != null && !"".equals(accusation.getAccusationType())){
			sqlWhere += " and accusationType like '%"+ accusation.getAccusationType() +"%'";
		}
		//查询主键
		if(accusation.getResourceId() != null && !"".equals(accusation.getResourceId())){
			sqlWhere += " and resourceId like '%"+ accusation.getResourceId() +"%'";
		}
		//查询创建人ID
		if(accusation.getCreatorId() != null && !"".equals(accusation.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ accusation.getCreatorId() +"%'";
		}
		//查询创建人
		if(accusation.getCreatorName() != null && !"".equals(accusation.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ accusation.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(accusation.getUpdaterId() != null && !"".equals(accusation.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ accusation.getUpdaterId() +"%'";
		}
		//查询修改人
		if(accusation.getUpdaterName() != null && !"".equals(accusation.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ accusation.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = accusationMapper.findByCondition(sqlWhere + limitSQL);
		int count = accusationMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Accusation> findByCondition(String sql){
		return accusationMapper.findByCondition(sql);
	}
	
	public List<Accusation> executeSQL(String sql){
		return accusationMapper.executeSQL(sql);
	}
}
