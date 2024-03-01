package com.info.view.file.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.file.mapper.FileUploadMapper;
import com.info.view.file.model.FileUpload;
import com.info.view.file.service.IFileUploadService;

@Service("fileUploadService")
public class FileUploadServiceImpl implements IFileUploadService {
	
	@Autowired
	private FileUploadMapper fileUploadMapper;


	public void addFileUpload(FileUpload fileUpload) {
		fileUploadMapper.addFileUpload(fileUpload);
	}


	public List<FileUpload> findSQL(String sql) {
		return fileUploadMapper.executeSQL(sql);
	}

	
}
