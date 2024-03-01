package com.info.view.register.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.register.mapper.RegisterMapper;
import com.info.view.register.model.Register;
import com.info.view.register.service.IRegisterService;

@Service("registerService")
public class RegisterServiceImpl implements IRegisterService {
	
	@Autowired
	private RegisterMapper registerMapper;


	public void addRegister(Register register) {
		registerMapper.addRegister(register);
	}

	public void updateRegister(Register register) {
		registerMapper.updateRegister(register);
	}

	public void deteleRegister(String id) {
		registerMapper.deteleRegister(id);
	}

	public List<Register> findAll() {
		return registerMapper.findAll();
	}

	public Register findById(String id) {
		return registerMapper.findById(id);
	}

	public Register login(String registerNo, String password) {
		List<Register> list = registerMapper.findByCondition(" and registerNo='" + registerNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Register)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Register register) {
		String sqlWhere = "";
		//查询名称
		if(register.getName() != null && !"".equals(register.getName())){
			sqlWhere += " and name like '%"+ register.getName() +"%'";
		}
		//查询电话
		if(register.getMobile() != null && !"".equals(register.getMobile())){
			sqlWhere += " and mobile like '%"+ register.getMobile() +"%'";
		}
		//查询地址
		if(register.getAddress() != null && !"".equals(register.getAddress())){
			sqlWhere += " and address like '%"+ register.getAddress() +"%'";
		}
		//查询体温
		if(register.getTw() != null && !"".equals(register.getTw())){
			sqlWhere += " and tw like '%"+ register.getTw() +"%'";
		}
		//查询来处
		if(register.getLc() != null && !"".equals(register.getLc())){
			sqlWhere += " and lc like '%"+ register.getLc() +"%'";
		}
		//查询其他信息
		if(register.getOtherInfo() != null && !"".equals(register.getOtherInfo())){
			sqlWhere += " and otherInfo like '%"+ register.getOtherInfo() +"%'";
		}
		//查询主键
		if(register.getResourceId() != null && !"".equals(register.getResourceId())){
			sqlWhere += " and resourceId like '%"+ register.getResourceId() +"%'";
		}
		//查询创建人ID
		if(register.getCreatorId() != null && !"".equals(register.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ register.getCreatorId() +"%'";
		}
		//查询登记人
		if(register.getCreatorName() != null && !"".equals(register.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ register.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(register.getUpdaterId() != null && !"".equals(register.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ register.getUpdaterId() +"%'";
		}
		//查询修改人
		if(register.getUpdaterName() != null && !"".equals(register.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ register.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = registerMapper.findByCondition(sqlWhere + limitSQL);
		int count = registerMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Register> findByCondition(String sql){
		return registerMapper.findByCondition(sql);
	}
	
	public List<Register> executeSQL(String sql){
		return registerMapper.executeSQL(sql);
	}
}
