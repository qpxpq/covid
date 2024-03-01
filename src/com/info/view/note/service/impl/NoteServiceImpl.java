package com.info.view.note.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.note.mapper.NoteMapper;
import com.info.view.note.model.Note;
import com.info.view.note.service.INoteService;

@Service("noteService")
public class NoteServiceImpl implements INoteService {
	
	@Autowired
	private NoteMapper noteMapper;


	public void addNote(Note note) {
		noteMapper.addNote(note);
	}

	public void updateNote(Note note) {
		noteMapper.updateNote(note);
	}

	public void deteleNote(String id) {
		noteMapper.deteleNote(id);
	}

	public List<Note> findAll() {
		return noteMapper.findAll();
	}

	public Note findById(String id) {
		return noteMapper.findById(id);
	}

	public Note login(String noteNo, String password) {
		List<Note> list = noteMapper.findByCondition(" and noteNo='" + noteNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Note)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Note note) {
		String sqlWhere = "";
		//查询帖子标题
		if(note.getNoteName() != null && !"".equals(note.getNoteName())){
			sqlWhere += " and noteName like '%"+ note.getNoteName() +"%'";
		}
		//查询帖子内容
		if(note.getNoteContext() != null && !"".equals(note.getNoteContext())){
			sqlWhere += " and noteContext like '%"+ note.getNoteContext() +"%'";
		}
		//查询主键
		if(note.getResourceId() != null && !"".equals(note.getResourceId())){
			sqlWhere += " and resourceId like '%"+ note.getResourceId() +"%'";
		}
		//查询创建人ID
		if(note.getCreatorId() != null && !"".equals(note.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ note.getCreatorId() +"%'";
		}
		//查询创建人
		if(note.getCreatorName() != null && !"".equals(note.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ note.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(note.getUpdaterId() != null && !"".equals(note.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ note.getUpdaterId() +"%'";
		}
		//查询修改人
		if(note.getUpdaterName() != null && !"".equals(note.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ note.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = noteMapper.findByCondition(sqlWhere + limitSQL);
		int count = noteMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Note> findByCondition(String sql){
		return noteMapper.findByCondition(sql);
	}
	
	public List<Note> executeSQL(String sql){
		return noteMapper.executeSQL(sql);
	}
}
