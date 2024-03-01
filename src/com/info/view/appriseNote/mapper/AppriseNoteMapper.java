package com.info.view.appriseNote.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.appriseNote.model.AppriseNote;

public interface AppriseNoteMapper {
	
	public void addAppriseNote(AppriseNote appriseNote);
	
	public void updateAppriseNote(AppriseNote appriseNote);
	
	public void deteleAppriseNote(String id);
	
	public List<AppriseNote> findAll();
	
	public AppriseNote findById(String id);
	
	public List<AppriseNote> findByCondition(@Param(value = "hql")String hql);
	
	public List<AppriseNote> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
