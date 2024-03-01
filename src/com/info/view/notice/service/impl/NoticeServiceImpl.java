package com.info.view.notice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.notice.mapper.NoticeMapper;
import com.info.view.notice.model.Notice;
import com.info.view.notice.service.INoticeService;

@Service("noticeService")
public class NoticeServiceImpl implements INoticeService {
	
	@Autowired
	private NoticeMapper noticeMapper;


	public void addNotice(Notice notice) {
		noticeMapper.addNotice(notice);
	}

	public void updateNotice(Notice notice) {
		noticeMapper.updateNotice(notice);
	}

	public void deteleNotice(String id) {
		noticeMapper.deteleNotice(id);
	}

	public List<Notice> findAll() {
		return noticeMapper.findAll();
	}

	public Notice findById(String id) {
		return noticeMapper.findById(id);
	}

	public Notice login(String noticeNo, String password) {
		List<Notice> list = noticeMapper.findByCondition(" and noticeNo='" + noticeNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Notice)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Notice notice) {
		String sqlWhere = "";
		//查询公告名称
		if(notice.getNoticeName() != null && !"".equals(notice.getNoticeName())){
			sqlWhere += " and noticeName like '%"+ notice.getNoticeName() +"%'";
		}
		//查询公告内容
		if(notice.getNoticeContext() != null && !"".equals(notice.getNoticeContext())){
			sqlWhere += " and noticeContext like '%"+ notice.getNoticeContext() +"%'";
		}
		//查询主键
		if(notice.getResourceId() != null && !"".equals(notice.getResourceId())){
			sqlWhere += " and resourceId like '%"+ notice.getResourceId() +"%'";
		}
		//查询创建人ID
		if(notice.getCreatorId() != null && !"".equals(notice.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ notice.getCreatorId() +"%'";
		}
		//查询创建人
		if(notice.getCreatorName() != null && !"".equals(notice.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ notice.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(notice.getUpdaterId() != null && !"".equals(notice.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ notice.getUpdaterId() +"%'";
		}
		//查询修改人
		if(notice.getUpdaterName() != null && !"".equals(notice.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ notice.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = noticeMapper.findByCondition(sqlWhere + limitSQL);
		int count = noticeMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Notice> findByCondition(String sql){
		return noticeMapper.findByCondition(sql);
	}
	
	public List<Notice> executeSQL(String sql){
		return noticeMapper.executeSQL(sql);
	}
}
