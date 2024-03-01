package com.info.view.stack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.stack.mapper.StackMapper;
import com.info.view.stack.model.Stack;
import com.info.view.stack.service.IStackService;

@Service("stackService")
public class StackServiceImpl implements IStackService {
	
	@Autowired
	private StackMapper stackMapper;


	public void addStack(Stack stack) {
		stackMapper.addStack(stack);
	}

	public void updateStack(Stack stack) {
		stackMapper.updateStack(stack);
	}

	public void deteleStack(String id) {
		stackMapper.deteleStack(id);
	}

	public List<Stack> findAll() {
		return stackMapper.findAll();
	}

	public Stack findById(String id) {
		return stackMapper.findById(id);
	}

	public Stack login(String stackNo, String password) {
		List<Stack> list = stackMapper.findByCondition(" and stackNo='" + stackNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Stack)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Stack stack) {
		String sqlWhere = "";
		//查询姓名
		if(stack.getName() != null && !"".equals(stack.getName())){
			sqlWhere += " and name like '%"+ stack.getName() +"%'";
		}
		//查询体温
		if(stack.getTw() != null && !"".equals(stack.getTw())){
			sqlWhere += " and tw like '%"+ stack.getTw() +"%'";
		}
		//查询图片
		if(stack.getFileName() != null && !"".equals(stack.getFileName())){
			sqlWhere += " and fileName like '%"+ stack.getFileName() +"%'";
		}
		//查询图片
		if(stack.getFileURL() != null && !"".equals(stack.getFileURL())){
			sqlWhere += " and fileURL like '%"+ stack.getFileURL() +"%'";
		}
		//查询状态
		if(stack.getStackType() != null && !"".equals(stack.getStackType())){
			sqlWhere += " and stackType like '%"+ stack.getStackType() +"%'";
		}
		//查询主键
		if(stack.getResourceId() != null && !"".equals(stack.getResourceId())){
			sqlWhere += " and resourceId like '%"+ stack.getResourceId() +"%'";
		}
		//查询创建人ID
		if(stack.getCreatorId() != null && !"".equals(stack.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ stack.getCreatorId() +"%'";
		}
		//查询创建人
		if(stack.getCreatorName() != null && !"".equals(stack.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ stack.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(stack.getUpdaterId() != null && !"".equals(stack.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ stack.getUpdaterId() +"%'";
		}
		//查询修改人
		if(stack.getUpdaterName() != null && !"".equals(stack.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ stack.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = stackMapper.findByCondition(sqlWhere + limitSQL);
		int count = stackMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Stack> findByCondition(String sql){
		return stackMapper.findByCondition(sql);
	}
	
	public List<Stack> executeSQL(String sql){
		return stackMapper.executeSQL(sql);
	}
}
