package com.info.view.file.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.info.common.util.DownFileHelper;

@Controller
public class FileUploadController {
	
	@RequestMapping("downFile")
	public void downFile(String filePath,String fileName,HttpServletResponse response){
		DownFileHelper.downFile(response, filePath, fileName);
	}
}