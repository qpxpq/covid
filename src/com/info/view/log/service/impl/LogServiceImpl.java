package com.info.view.log.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.InitEntity;
import com.info.common.util.PagerList;
import com.info.view.log.mapper.LogMapper;
import com.info.view.log.model.Log;
import com.info.view.log.service.ILogService;

@Service("logService")
public class LogServiceImpl implements ILogService {
	
	@Autowired
	private LogMapper logMapper;
	
	public void addLogInfo(HttpServletRequest request){
		String url = request.getRequestURL().toString();//获得客户端发送请求的完整url
		Log log = new Log();
		log.setLogAddress(url);
		try {
			InitEntity initEntity = new InitEntity();
			log.setResourceId(UUID.randomUUID().toString().replace("-",""));
			log = initEntity.initAddInfo(log, request.getSession());
			logMapper.addLog(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void addLog(Log log) {
		logMapper.addLog(log);
	}

	public void updateLog(Log log) {
		logMapper.updateLog(log);
	}

	public void deteleLog(String id) {
		logMapper.deteleLog(id);
	}

	public List<Log> findAll() {
		return logMapper.findAll();
	}

	public Log findById(String id) {
		return logMapper.findById(id);
	}

	public Log login(String logNo, String password) {
		List<Log> list = logMapper.findByCondition(" and logNo='" + logNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Log)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Log log) {
		String sqlWhere = "";
		//查询操作地址
		if(log.getLogAddress() != null && !"".equals(log.getLogAddress())){
			sqlWhere += " and logAddress like '%"+ log.getLogAddress() +"%'";
		}
		//查询主键
		if(log.getResourceId() != null && !"".equals(log.getResourceId())){
			sqlWhere += " and resourceId like '%"+ log.getResourceId() +"%'";
		}
		//查询创建人ID
		if(log.getCreatorId() != null && !"".equals(log.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ log.getCreatorId() +"%'";
		}
		//查询创建人
		if(log.getCreatorName() != null && !"".equals(log.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ log.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(log.getUpdaterId() != null && !"".equals(log.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ log.getUpdaterId() +"%'";
		}
		//查询修改人
		if(log.getUpdaterName() != null && !"".equals(log.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ log.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = logMapper.findByCondition(sqlWhere + limitSQL);
		int count = logMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Log> findByCondition(String sql){
		return logMapper.findByCondition(sql);
	}
	
	public List<Log> executeSQL(String sql){
		return logMapper.executeSQL(sql);
	}
}
