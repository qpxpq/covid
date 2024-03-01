package com.info.view.file.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.file.model.FileUpload;

public interface IFileUploadService {
	
	public void addFileUpload(FileUpload fileUpload);
	
	
	public List<FileUpload> findSQL(String sql);
	
}
