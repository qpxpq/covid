package com.info.view.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.news.mapper.NewsMapper;
import com.info.view.news.model.News;
import com.info.view.news.service.INewsService;

@Service("newsService")
public class NewsServiceImpl implements INewsService {
	
	@Autowired
	private NewsMapper newsMapper;


	public void addNews(News news) {
		newsMapper.addNews(news);
	}

	public void updateNews(News news) {
		newsMapper.updateNews(news);
	}

	public void deteleNews(String id) {
		newsMapper.deteleNews(id);
	}

	public List<News> findAll() {
		return newsMapper.findAll();
	}

	public News findById(String id) {
		return newsMapper.findById(id);
	}

	public News login(String newsNo, String password) {
		List<News> list = newsMapper.findByCondition(" and newsNo='" + newsNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (News)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,News news) {
		String sqlWhere = "";
		//查询消息标题
		if(news.getMessageName() != null && !"".equals(news.getMessageName())){
			sqlWhere += " and messageName like '%"+ news.getMessageName() +"%'";
		}
		//查询消息内容
		if(news.getMessageContext() != null && !"".equals(news.getMessageContext())){
			sqlWhere += " and messageContext like '%"+ news.getMessageContext() +"%'";
		}
		//查询用户Id
		if(news.getUserId() != null && !"".equals(news.getUserId())){
			sqlWhere += " and userId like '%"+ news.getUserId() +"%'";
		}
		//查询用户名称
		if(news.getUserName() != null && !"".equals(news.getUserName())){
			sqlWhere += " and userName like '%"+ news.getUserName() +"%'";
		}
		//查询主键
		if(news.getResourceId() != null && !"".equals(news.getResourceId())){
			sqlWhere += " and resourceId like '%"+ news.getResourceId() +"%'";
		}
		//查询创建人ID
		if(news.getCreatorId() != null && !"".equals(news.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ news.getCreatorId() +"%'";
		}
		//查询创建人
		if(news.getCreatorName() != null && !"".equals(news.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ news.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(news.getUpdaterId() != null && !"".equals(news.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ news.getUpdaterId() +"%'";
		}
		//查询修改人
		if(news.getUpdaterName() != null && !"".equals(news.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ news.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = newsMapper.findByCondition(sqlWhere + limitSQL);
		int count = newsMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<News> findByCondition(String sql){
		return newsMapper.findByCondition(sql);
	}
	
	public List<News> executeSQL(String sql){
		return newsMapper.executeSQL(sql);
	}
}
