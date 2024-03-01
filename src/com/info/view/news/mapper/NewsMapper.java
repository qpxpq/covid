package com.info.view.news.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.news.model.News;

public interface NewsMapper {
	
	public void addNews(News news);
	
	public void updateNews(News news);
	
	public void deteleNews(String id);
	
	public List<News> findAll();
	
	public News findById(String id);
	
	public List<News> findByCondition(@Param(value = "hql")String hql);
	
	public List<News> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
