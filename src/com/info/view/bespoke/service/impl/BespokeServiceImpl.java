package com.info.view.bespoke.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.bespoke.mapper.BespokeMapper;
import com.info.view.bespoke.model.Bespoke;
import com.info.view.bespoke.service.IBespokeService;

@Service("bespokeService")
public class BespokeServiceImpl implements IBespokeService {
	
	@Autowired
	private BespokeMapper bespokeMapper;


	public void addBespoke(Bespoke bespoke) {
		bespokeMapper.addBespoke(bespoke);
	}

	public void updateBespoke(Bespoke bespoke) {
		bespokeMapper.updateBespoke(bespoke);
	}

	public void deteleBespoke(String id) {
		bespokeMapper.deteleBespoke(id);
	}

	public List<Bespoke> findAll() {
		return bespokeMapper.findAll();
	}

	public Bespoke findById(String id) {
		return bespokeMapper.findById(id);
	}

	public Bespoke login(String bespokeNo, String password) {
		List<Bespoke> list = bespokeMapper.findByCondition(" and bespokeNo='" + bespokeNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Bespoke)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Bespoke bespoke) {
		String sqlWhere = "";
		//查询外键
		if(bespoke.getVaccinumId() != null && !"".equals(bespoke.getVaccinumId())){
			sqlWhere += " and vaccinumId like '%"+ bespoke.getVaccinumId() +"%'";
		}
		//查询疫苗名称
		if(bespoke.getVaccinumName() != null && !"".equals(bespoke.getVaccinumName())){
			sqlWhere += " and vaccinumName like '%"+ bespoke.getVaccinumName() +"%'";
		}
		//查询疫苗针次
		if(bespoke.getVaccinumType() != null && !"".equals(bespoke.getVaccinumType())){
			sqlWhere += " and vaccinumType like '%"+ bespoke.getVaccinumType() +"%'";
		}
		//查询用户Id
		if(bespoke.getUserId() != null && !"".equals(bespoke.getUserId())){
			sqlWhere += " and userId like '%"+ bespoke.getUserId() +"%'";
		}
		//查询用户名称
		if(bespoke.getUserName() != null && !"".equals(bespoke.getUserName())){
			sqlWhere += " and userName like '%"+ bespoke.getUserName() +"%'";
		}
		//查询审核人
		if(bespoke.getAuthorId() != null && !"".equals(bespoke.getAuthorId())){
			sqlWhere += " and authorId like '%"+ bespoke.getAuthorId() +"%'";
		}
		//查询审核人
		if(bespoke.getAuthorName() != null && !"".equals(bespoke.getAuthorName())){
			sqlWhere += " and authorName like '%"+ bespoke.getAuthorName() +"%'";
		}
		//查询回复内容
		if(bespoke.getAuthorContext() != null && !"".equals(bespoke.getAuthorContext())){
			sqlWhere += " and authorContext like '%"+ bespoke.getAuthorContext() +"%'";
		}
		//查询状态
		if(bespoke.getAccusationType() != null && !"".equals(bespoke.getAccusationType())){
			sqlWhere += " and accusationType like '%"+ bespoke.getAccusationType() +"%'";
		}
		//查询主键
		if(bespoke.getResourceId() != null && !"".equals(bespoke.getResourceId())){
			sqlWhere += " and resourceId like '%"+ bespoke.getResourceId() +"%'";
		}
		//查询创建人ID
		if(bespoke.getCreatorId() != null && !"".equals(bespoke.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ bespoke.getCreatorId() +"%'";
		}
		//查询创建人
		if(bespoke.getCreatorName() != null && !"".equals(bespoke.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ bespoke.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(bespoke.getUpdaterId() != null && !"".equals(bespoke.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ bespoke.getUpdaterId() +"%'";
		}
		//查询修改人
		if(bespoke.getUpdaterName() != null && !"".equals(bespoke.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ bespoke.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = bespokeMapper.findByCondition(sqlWhere + limitSQL);
		int count = bespokeMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Bespoke> findByCondition(String sql){
		return bespokeMapper.findByCondition(sql);
	}
	
	public List<Bespoke> executeSQL(String sql){
		return bespokeMapper.executeSQL(sql);
	}
}
