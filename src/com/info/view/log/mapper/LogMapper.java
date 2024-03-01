package com.info.view.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.log.model.Log;

public interface LogMapper {
	
	public void addLog(Log log);
	
	public void updateLog(Log log);
	
	public void deteleLog(String id);
	
	public List<Log> findAll();
	
	public Log findById(String id);
	
	public List<Log> findByCondition(@Param(value = "hql")String hql);
	
	public List<Log> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
