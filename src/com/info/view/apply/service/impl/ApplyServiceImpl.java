package com.info.view.apply.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.apply.mapper.ApplyMapper;
import com.info.view.apply.model.Apply;
import com.info.view.apply.service.IApplyService;

@Service("applyService")
public class ApplyServiceImpl implements IApplyService {
	
	@Autowired
	private ApplyMapper applyMapper;


	public void addApply(Apply apply) {
		applyMapper.addApply(apply);
	}

	public void updateApply(Apply apply) {
		applyMapper.updateApply(apply);
	}

	public void deteleApply(String id) {
		applyMapper.deteleApply(id);
	}

	public List<Apply> findAll() {
		return applyMapper.findAll();
	}

	public Apply findById(String id) {
		return applyMapper.findById(id);
	}

	public Apply login(String applyNo, String password) {
		List<Apply> list = applyMapper.findByCondition(" and applyNo='" + applyNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Apply)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Apply apply) {
		String sqlWhere = "";
		//查询申请标题
		if(apply.getApplyName() != null && !"".equals(apply.getApplyName())){
			sqlWhere += " and applyName like '%"+ apply.getApplyName() +"%'";
		}
		//查询申请内容
		if(apply.getApplyContext() != null && !"".equals(apply.getApplyContext())){
			sqlWhere += " and applyContext like '%"+ apply.getApplyContext() +"%'";
		}
		//查询用户Id
		if(apply.getUserId() != null && !"".equals(apply.getUserId())){
			sqlWhere += " and userId like '%"+ apply.getUserId() +"%'";
		}
		//查询申请人
		if(apply.getUserName() != null && !"".equals(apply.getUserName())){
			sqlWhere += " and userName like '%"+ apply.getUserName() +"%'";
		}
		//查询审核人
		if(apply.getAuthorId() != null && !"".equals(apply.getAuthorId())){
			sqlWhere += " and authorId like '%"+ apply.getAuthorId() +"%'";
		}
		//查询审核人
		if(apply.getAuthorName() != null && !"".equals(apply.getAuthorName())){
			sqlWhere += " and authorName like '%"+ apply.getAuthorName() +"%'";
		}
		//查询回复内容
		if(apply.getAuthorContext() != null && !"".equals(apply.getAuthorContext())){
			sqlWhere += " and authorContext like '%"+ apply.getAuthorContext() +"%'";
		}
		//查询状态
		if(apply.getApplyType() != null && !"".equals(apply.getApplyType())){
			sqlWhere += " and applyType like '%"+ apply.getApplyType() +"%'";
		}
		//查询主键
		if(apply.getResourceId() != null && !"".equals(apply.getResourceId())){
			sqlWhere += " and resourceId like '%"+ apply.getResourceId() +"%'";
		}
		//查询创建人ID
		if(apply.getCreatorId() != null && !"".equals(apply.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ apply.getCreatorId() +"%'";
		}
		//查询创建人
		if(apply.getCreatorName() != null && !"".equals(apply.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ apply.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(apply.getUpdaterId() != null && !"".equals(apply.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ apply.getUpdaterId() +"%'";
		}
		//查询修改人
		if(apply.getUpdaterName() != null && !"".equals(apply.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ apply.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = applyMapper.findByCondition(sqlWhere + limitSQL);
		int count = applyMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Apply> findByCondition(String sql){
		return applyMapper.findByCondition(sql);
	}
	
	public List<Apply> executeSQL(String sql){
		return applyMapper.executeSQL(sql);
	}
}
