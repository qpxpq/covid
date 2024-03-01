package com.info.view.note.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.note.model.Note;

public interface INoteService {
	
	public void addNote(Note note);
	
	public void updateNote(Note note);
	
	public void deteleNote(String id);
	
	public List<Note> findAll();
	
	public Note findById(String id);
	
	public Note login(String noteNo,String password);

	public PagerList findPagerList(PagerList pagerList, Note note);

	public List<Note> findByCondition(String sql);
	
	public List<Note> executeSQL(String sql);
}
