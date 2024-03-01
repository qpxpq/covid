package com.info.view.news.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.news.model.News;

public interface INewsService {
	
	public void addNews(News news);
	
	public void updateNews(News news);
	
	public void deteleNews(String id);
	
	public List<News> findAll();
	
	public News findById(String id);
	
	public News login(String newsNo,String password);

	public PagerList findPagerList(PagerList pagerList, News news);

	public List<News> findByCondition(String sql);
	
	public List<News> executeSQL(String sql);
}
