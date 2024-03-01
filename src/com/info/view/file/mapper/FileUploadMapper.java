package com.info.view.file.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.file.model.FileUpload;


public interface FileUploadMapper {
	
	public void addFileUpload(FileUpload fileUpload);
	
	public List<FileUpload> executeSQL(@Param(value = "sql")String sql);
	
}
