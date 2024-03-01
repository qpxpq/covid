package com.info.view.appriseNote.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.appriseNote.mapper.AppriseNoteMapper;
import com.info.view.appriseNote.model.AppriseNote;
import com.info.view.appriseNote.service.IAppriseNoteService;

@Service("appriseNoteService")
public class AppriseNoteServiceImpl implements IAppriseNoteService {
	
	@Autowired
	private AppriseNoteMapper appriseNoteMapper;


	public void addAppriseNote(AppriseNote appriseNote) {
		appriseNoteMapper.addAppriseNote(appriseNote);
	}

	public void updateAppriseNote(AppriseNote appriseNote) {
		appriseNoteMapper.updateAppriseNote(appriseNote);
	}

	public void deteleAppriseNote(String id) {
		appriseNoteMapper.deteleAppriseNote(id);
	}

	public List<AppriseNote> findAll() {
		return appriseNoteMapper.findAll();
	}

	public AppriseNote findById(String id) {
		return appriseNoteMapper.findById(id);
	}

	public AppriseNote login(String appriseNoteNo, String password) {
		List<AppriseNote> list = appriseNoteMapper.findByCondition(" and appriseNoteNo='" + appriseNoteNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (AppriseNote)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,AppriseNote appriseNote) {
		String sqlWhere = "";
		//查询帖子外键
		if(appriseNote.getNoteId() != null && !"".equals(appriseNote.getNoteId())){
			sqlWhere += " and noteId like '%"+ appriseNote.getNoteId() +"%'";
		}
		//查询帖子标题
		if(appriseNote.getNoteName() != null && !"".equals(appriseNote.getNoteName())){
			sqlWhere += " and noteName like '%"+ appriseNote.getNoteName() +"%'";
		}
		//查询回复内容
		if(appriseNote.getAppriseNote() != null && !"".equals(appriseNote.getAppriseNote())){
			sqlWhere += " and appriseNote like '%"+ appriseNote.getAppriseNote() +"%'";
		}
		//查询主键
		if(appriseNote.getResourceId() != null && !"".equals(appriseNote.getResourceId())){
			sqlWhere += " and resourceId like '%"+ appriseNote.getResourceId() +"%'";
		}
		//查询创建人ID
		if(appriseNote.getCreatorId() != null && !"".equals(appriseNote.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ appriseNote.getCreatorId() +"%'";
		}
		//查询回复人
		if(appriseNote.getCreatorName() != null && !"".equals(appriseNote.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ appriseNote.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(appriseNote.getUpdaterId() != null && !"".equals(appriseNote.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ appriseNote.getUpdaterId() +"%'";
		}
		//查询修改人
		if(appriseNote.getUpdaterName() != null && !"".equals(appriseNote.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ appriseNote.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = appriseNoteMapper.findByCondition(sqlWhere + limitSQL);
		int count = appriseNoteMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<AppriseNote> findByCondition(String sql){
		return appriseNoteMapper.findByCondition(sql);
	}
	
	public List<AppriseNote> executeSQL(String sql){
		return appriseNoteMapper.executeSQL(sql);
	}
}
