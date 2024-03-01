package com.info.view.disinfect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.disinfect.mapper.DisinfectMapper;
import com.info.view.disinfect.model.Disinfect;
import com.info.view.disinfect.service.IDisinfectService;

@Service("disinfectService")
public class DisinfectServiceImpl implements IDisinfectService {
	
	@Autowired
	private DisinfectMapper disinfectMapper;


	public void addDisinfect(Disinfect disinfect) {
		disinfectMapper.addDisinfect(disinfect);
	}

	public void updateDisinfect(Disinfect disinfect) {
		disinfectMapper.updateDisinfect(disinfect);
	}

	public void deteleDisinfect(String id) {
		disinfectMapper.deteleDisinfect(id);
	}

	public List<Disinfect> findAll() {
		return disinfectMapper.findAll();
	}

	public Disinfect findById(String id) {
		return disinfectMapper.findById(id);
	}

	public Disinfect login(String disinfectNo, String password) {
		List<Disinfect> list = disinfectMapper.findByCondition(" and disinfectNo='" + disinfectNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Disinfect)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Disinfect disinfect) {
		String sqlWhere = "";
		//查询申请标题
		if(disinfect.getDisinfectName() != null && !"".equals(disinfect.getDisinfectName())){
			sqlWhere += " and disinfectName like '%"+ disinfect.getDisinfectName() +"%'";
		}
		//查询申请内容
		if(disinfect.getDisinfectContext() != null && !"".equals(disinfect.getDisinfectContext())){
			sqlWhere += " and disinfectContext like '%"+ disinfect.getDisinfectContext() +"%'";
		}
		//查询用户Id
		if(disinfect.getUserId() != null && !"".equals(disinfect.getUserId())){
			sqlWhere += " and userId like '%"+ disinfect.getUserId() +"%'";
		}
		//查询申请人
		if(disinfect.getUserName() != null && !"".equals(disinfect.getUserName())){
			sqlWhere += " and userName like '%"+ disinfect.getUserName() +"%'";
		}
		//查询审核人
		if(disinfect.getAuthorId() != null && !"".equals(disinfect.getAuthorId())){
			sqlWhere += " and authorId like '%"+ disinfect.getAuthorId() +"%'";
		}
		//查询审核人
		if(disinfect.getAuthorName() != null && !"".equals(disinfect.getAuthorName())){
			sqlWhere += " and authorName like '%"+ disinfect.getAuthorName() +"%'";
		}
		//查询回复内容
		if(disinfect.getAuthorContext() != null && !"".equals(disinfect.getAuthorContext())){
			sqlWhere += " and authorContext like '%"+ disinfect.getAuthorContext() +"%'";
		}
		//查询状态
		if(disinfect.getDisinfectType() != null && !"".equals(disinfect.getDisinfectType())){
			sqlWhere += " and disinfectType like '%"+ disinfect.getDisinfectType() +"%'";
		}
		//查询主键
		if(disinfect.getResourceId() != null && !"".equals(disinfect.getResourceId())){
			sqlWhere += " and resourceId like '%"+ disinfect.getResourceId() +"%'";
		}
		//查询创建人ID
		if(disinfect.getCreatorId() != null && !"".equals(disinfect.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ disinfect.getCreatorId() +"%'";
		}
		//查询创建人
		if(disinfect.getCreatorName() != null && !"".equals(disinfect.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ disinfect.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(disinfect.getUpdaterId() != null && !"".equals(disinfect.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ disinfect.getUpdaterId() +"%'";
		}
		//查询修改人
		if(disinfect.getUpdaterName() != null && !"".equals(disinfect.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ disinfect.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = disinfectMapper.findByCondition(sqlWhere + limitSQL);
		int count = disinfectMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Disinfect> findByCondition(String sql){
		return disinfectMapper.findByCondition(sql);
	}
	
	public List<Disinfect> executeSQL(String sql){
		return disinfectMapper.executeSQL(sql);
	}
}
