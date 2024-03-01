package com.info.view.flow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.flow.mapper.FlowMapper;
import com.info.view.flow.model.Flow;
import com.info.view.flow.service.IFlowService;

@Service("flowService")
public class FlowServiceImpl implements IFlowService {
	
	@Autowired
	private FlowMapper flowMapper;


	public void addFlow(Flow flow) {
		flowMapper.addFlow(flow);
	}

	public void updateFlow(Flow flow) {
		flowMapper.updateFlow(flow);
	}

	public void deteleFlow(String id) {
		flowMapper.deteleFlow(id);
	}

	public List<Flow> findAll() {
		return flowMapper.findAll();
	}

	public Flow findById(String id) {
		return flowMapper.findById(id);
	}

	public Flow login(String flowNo, String password) {
		List<Flow> list = flowMapper.findByCondition(" and flowNo='" + flowNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Flow)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Flow flow) {
		String sqlWhere = "";
		//查询人员名称
		if(flow.getName() != null && !"".equals(flow.getName())){
			sqlWhere += " and name like '%"+ flow.getName() +"%'";
		}
		//查询联系电话
		if(flow.getMobile() != null && !"".equals(flow.getMobile())){
			sqlWhere += " and mobile like '%"+ flow.getMobile() +"%'";
		}
		//查询联系地址
		if(flow.getAddress() != null && !"".equals(flow.getAddress())){
			sqlWhere += " and address like '%"+ flow.getAddress() +"%'";
		}
		//查询户主名
		if(flow.getHz() != null && !"".equals(flow.getHz())){
			sqlWhere += " and hz like '%"+ flow.getHz() +"%'";
		}
		//查询户主手机号
		if(flow.getHzMobile() != null && !"".equals(flow.getHzMobile())){
			sqlWhere += " and hzMobile like '%"+ flow.getHzMobile() +"%'";
		}
		//查询入住日期
		if(flow.getFlowDate() != null && !"".equals(flow.getFlowDate())){
			sqlWhere += " and flowDate like '%"+ flow.getFlowDate() +"%'";
		}
		//查询状态
		if(flow.getFlowType() != null && !"".equals(flow.getFlowType())){
			sqlWhere += " and flowType like '%"+ flow.getFlowType() +"%'";
		}
		//查询单元号
		if(flow.getUnitNo() != null && !"".equals(flow.getUnitNo())){
			sqlWhere += " and unitNo like '%"+ flow.getUnitNo() +"%'";
		}
		//查询备注
		if(flow.getFlowContext() != null && !"".equals(flow.getFlowContext())){
			sqlWhere += " and flowContext like '%"+ flow.getFlowContext() +"%'";
		}
		//查询主键
		if(flow.getResourceId() != null && !"".equals(flow.getResourceId())){
			sqlWhere += " and resourceId like '%"+ flow.getResourceId() +"%'";
		}
		//查询创建人ID
		if(flow.getCreatorId() != null && !"".equals(flow.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ flow.getCreatorId() +"%'";
		}
		//查询创建人
		if(flow.getCreatorName() != null && !"".equals(flow.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ flow.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(flow.getUpdaterId() != null && !"".equals(flow.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ flow.getUpdaterId() +"%'";
		}
		//查询修改人
		if(flow.getUpdaterName() != null && !"".equals(flow.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ flow.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = flowMapper.findByCondition(sqlWhere + limitSQL);
		int count = flowMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Flow> findByCondition(String sql){
		return flowMapper.findByCondition(sql);
	}
	
	public List<Flow> executeSQL(String sql){
		return flowMapper.executeSQL(sql);
	}
}
