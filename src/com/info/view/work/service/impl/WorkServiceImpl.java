package com.info.view.work.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.work.mapper.WorkMapper;
import com.info.view.work.model.Work;
import com.info.view.work.service.IWorkService;

@Service("workService")
public class WorkServiceImpl implements IWorkService {
	
	@Autowired
	private WorkMapper workMapper;


	public void addWork(Work work) {
		workMapper.addWork(work);
	}

	public void updateWork(Work work) {
		workMapper.updateWork(work);
	}

	public void deteleWork(String id) {
		workMapper.deteleWork(id);
	}

	public List<Work> findAll() {
		return workMapper.findAll();
	}

	public Work findById(String id) {
		return workMapper.findById(id);
	}

	public Work login(String workNo, String password) {
		List<Work> list = workMapper.findByCondition(" and workNo='" + workNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Work)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Work work) {
		String sqlWhere = "";
		//查询账号
		if(work.getWorkNo() != null && !"".equals(work.getWorkNo())){
			sqlWhere += " and workNo like '%"+ work.getWorkNo() +"%'";
		}
		//查询密码
		if(work.getPassword() != null && !"".equals(work.getPassword())){
			sqlWhere += " and password like '%"+ work.getPassword() +"%'";
		}
		//查询姓名
		if(work.getWorkName() != null && !"".equals(work.getWorkName())){
			sqlWhere += " and workName like '%"+ work.getWorkName() +"%'";
		}
		//查询性别
		if(work.getSex() != null && !"".equals(work.getSex())){
			sqlWhere += " and sex like '%"+ work.getSex() +"%'";
		}
		//查询电话
		if(work.getMobile() != null && !"".equals(work.getMobile())){
			sqlWhere += " and mobile like '%"+ work.getMobile() +"%'";
		}
		//查询地址
		if(work.getAddress() != null && !"".equals(work.getAddress())){
			sqlWhere += " and address like '%"+ work.getAddress() +"%'";
		}
		//查询主键
		if(work.getResourceId() != null && !"".equals(work.getResourceId())){
			sqlWhere += " and resourceId like '%"+ work.getResourceId() +"%'";
		}
		//查询创建人ID
		if(work.getCreatorId() != null && !"".equals(work.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ work.getCreatorId() +"%'";
		}
		//查询创建人
		if(work.getCreatorName() != null && !"".equals(work.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ work.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(work.getUpdaterId() != null && !"".equals(work.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ work.getUpdaterId() +"%'";
		}
		//查询修改人
		if(work.getUpdaterName() != null && !"".equals(work.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ work.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = workMapper.findByCondition(sqlWhere + limitSQL);
		int count = workMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Work> findByCondition(String sql){
		return workMapper.findByCondition(sql);
	}
	
	public List<Work> executeSQL(String sql){
		return workMapper.executeSQL(sql);
	}
}
