package com.info.view.appriseNote.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.appriseNote.model.AppriseNote;

public interface IAppriseNoteService {
	
	public void addAppriseNote(AppriseNote appriseNote);
	
	public void updateAppriseNote(AppriseNote appriseNote);
	
	public void deteleAppriseNote(String id);
	
	public List<AppriseNote> findAll();
	
	public AppriseNote findById(String id);
	
	public AppriseNote login(String appriseNoteNo,String password);

	public PagerList findPagerList(PagerList pagerList, AppriseNote appriseNote);

	public List<AppriseNote> findByCondition(String sql);
	
	public List<AppriseNote> executeSQL(String sql);
}
