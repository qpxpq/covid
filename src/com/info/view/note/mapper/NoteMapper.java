package com.info.view.note.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.note.model.Note;

public interface NoteMapper {
	
	public void addNote(Note note);
	
	public void updateNote(Note note);
	
	public void deteleNote(String id);
	
	public List<Note> findAll();
	
	public Note findById(String id);
	
	public List<Note> findByCondition(@Param(value = "hql")String hql);
	
	public List<Note> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
