package com.info.view.file.model;

/**
 * Person entity. @author MyEclipse Persistence Tools
 */

public class FileUpload implements java.io.Serializable {

	private String resourceId;
	private String fileOldName;
	private String fileNewName;
	private String filePath;
	private String fileSuffix;
	private String id;
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getFileOldName() {
		return fileOldName;
	}
	public void setFileOldName(String fileOldName) {
		this.fileOldName = fileOldName;
	}
	public String getFileNewName() {
		return fileNewName;
	}
	public void setFileNewName(String fileNewName) {
		this.fileNewName = fileNewName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileSuffix() {
		return fileSuffix;
	}
	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}