package com.info.view.vaccinum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.vaccinum.mapper.VaccinumMapper;
import com.info.view.vaccinum.model.Vaccinum;
import com.info.view.vaccinum.service.IVaccinumService;

@Service("vaccinumService")
public class VaccinumServiceImpl implements IVaccinumService {
	
	@Autowired
	private VaccinumMapper vaccinumMapper;


	public void addVaccinum(Vaccinum vaccinum) {
		vaccinumMapper.addVaccinum(vaccinum);
	}

	public void updateVaccinum(Vaccinum vaccinum) {
		vaccinumMapper.updateVaccinum(vaccinum);
	}

	public void deteleVaccinum(String id) {
		vaccinumMapper.deteleVaccinum(id);
	}

	public List<Vaccinum> findAll() {
		return vaccinumMapper.findAll();
	}

	public Vaccinum findById(String id) {
		return vaccinumMapper.findById(id);
	}

	public Vaccinum login(String vaccinumNo, String password) {
		List<Vaccinum> list = vaccinumMapper.findByCondition(" and vaccinumNo='" + vaccinumNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Vaccinum)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Vaccinum vaccinum) {
		String sqlWhere = "";
		//查询疫苗名称
		if(vaccinum.getVaccinumName() != null && !"".equals(vaccinum.getVaccinumName())){
			sqlWhere += " and vaccinumName like '%"+ vaccinum.getVaccinumName() +"%'";
		}
		//查询疫苗描述
		if(vaccinum.getVaccinumContext() != null && !"".equals(vaccinum.getVaccinumContext())){
			sqlWhere += " and vaccinumContext like '%"+ vaccinum.getVaccinumContext() +"%'";
		}
		//查询疫苗针次
		if(vaccinum.getVaccinumType() != null && !"".equals(vaccinum.getVaccinumType())){
			sqlWhere += " and vaccinumType like '%"+ vaccinum.getVaccinumType() +"%'";
		}
		//查询接种地点
		if(vaccinum.getAddress() != null && !"".equals(vaccinum.getAddress())){
			sqlWhere += " and address like '%"+ vaccinum.getAddress() +"%'";
		}
		//查询主键
		if(vaccinum.getResourceId() != null && !"".equals(vaccinum.getResourceId())){
			sqlWhere += " and resourceId like '%"+ vaccinum.getResourceId() +"%'";
		}
		//查询创建人ID
		if(vaccinum.getCreatorId() != null && !"".equals(vaccinum.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ vaccinum.getCreatorId() +"%'";
		}
		//查询创建人
		if(vaccinum.getCreatorName() != null && !"".equals(vaccinum.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ vaccinum.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(vaccinum.getUpdaterId() != null && !"".equals(vaccinum.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ vaccinum.getUpdaterId() +"%'";
		}
		//查询修改人
		if(vaccinum.getUpdaterName() != null && !"".equals(vaccinum.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ vaccinum.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = vaccinumMapper.findByCondition(sqlWhere + limitSQL);
		int count = vaccinumMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Vaccinum> findByCondition(String sql){
		return vaccinumMapper.findByCondition(sql);
	}
	
	public List<Vaccinum> executeSQL(String sql){
		return vaccinumMapper.executeSQL(sql);
	}
}
