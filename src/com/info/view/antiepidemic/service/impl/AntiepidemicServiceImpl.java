package com.info.view.antiepidemic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.antiepidemic.mapper.AntiepidemicMapper;
import com.info.view.antiepidemic.model.Antiepidemic;
import com.info.view.antiepidemic.service.IAntiepidemicService;

@Service("antiepidemicService")
public class AntiepidemicServiceImpl implements IAntiepidemicService {
	
	@Autowired
	private AntiepidemicMapper antiepidemicMapper;


	public void addAntiepidemic(Antiepidemic antiepidemic) {
		antiepidemicMapper.addAntiepidemic(antiepidemic);
	}

	public void updateAntiepidemic(Antiepidemic antiepidemic) {
		antiepidemicMapper.updateAntiepidemic(antiepidemic);
	}

	public void deteleAntiepidemic(String id) {
		antiepidemicMapper.deteleAntiepidemic(id);
	}

	public List<Antiepidemic> findAll() {
		return antiepidemicMapper.findAll();
	}

	public Antiepidemic findById(String id) {
		return antiepidemicMapper.findById(id);
	}

	public Antiepidemic login(String antiepidemicNo, String password) {
		List<Antiepidemic> list = antiepidemicMapper.findByCondition(" and antiepidemicNo='" + antiepidemicNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Antiepidemic)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Antiepidemic antiepidemic) {
		String sqlWhere = "";
		//查询防疫信息标题
		if(antiepidemic.getAntiepidemicName() != null && !"".equals(antiepidemic.getAntiepidemicName())){
			sqlWhere += " and antiepidemicName like '%"+ antiepidemic.getAntiepidemicName() +"%'";
		}
		//查询防疫信息描述
		if(antiepidemic.getAntiepidemicContext() != null && !"".equals(antiepidemic.getAntiepidemicContext())){
			sqlWhere += " and antiepidemicContext like '%"+ antiepidemic.getAntiepidemicContext() +"%'";
		}
		//查询防疫信息要求
		if(antiepidemic.getAntiepidemicYq() != null && !"".equals(antiepidemic.getAntiepidemicYq())){
			sqlWhere += " and antiepidemicYq like '%"+ antiepidemic.getAntiepidemicYq() +"%'";
		}
		//查询主键
		if(antiepidemic.getResourceId() != null && !"".equals(antiepidemic.getResourceId())){
			sqlWhere += " and resourceId like '%"+ antiepidemic.getResourceId() +"%'";
		}
		//查询创建人ID
		if(antiepidemic.getCreatorId() != null && !"".equals(antiepidemic.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ antiepidemic.getCreatorId() +"%'";
		}
		//查询创建人
		if(antiepidemic.getCreatorName() != null && !"".equals(antiepidemic.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ antiepidemic.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(antiepidemic.getUpdaterId() != null && !"".equals(antiepidemic.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ antiepidemic.getUpdaterId() +"%'";
		}
		//查询修改人
		if(antiepidemic.getUpdaterName() != null && !"".equals(antiepidemic.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ antiepidemic.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = antiepidemicMapper.findByCondition(sqlWhere + limitSQL);
		int count = antiepidemicMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Antiepidemic> findByCondition(String sql){
		return antiepidemicMapper.findByCondition(sql);
	}
	
	public List<Antiepidemic> executeSQL(String sql){
		return antiepidemicMapper.executeSQL(sql);
	}
}
