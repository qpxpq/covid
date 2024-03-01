package com.info.view.stack.model;

import java.util.Date;

/**
 * 健康码记录
 */
public class Stack implements java.io.Serializable {
    	private String name; //姓名
    	private String tw; //体温
    	private String fileName; //图片
    	private String fileURL; //图片
    	private String stackType; //状态
    	private String resourceId; //主键
    	private String creatorId; //创建人ID
    	private String creatorName; //创建人
    	private String updaterId; //修改人ID
    	private String updaterName; //修改人
    	private Date updateTime; //修改时间
    	private Date createTime; //创建时间
	
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTw() {
			return tw;
		}
		public void setTw(String tw) {
			this.tw = tw;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFileURL() {
			return fileURL;
		}
		public void setFileURL(String fileURL) {
			this.fileURL = fileURL;
		}
		public String getStackType() {
			return stackType;
		}
		public void setStackType(String stackType) {
			this.stackType = stackType;
		}
		public String getResourceId() {
			return resourceId;
		}
		public void setResourceId(String resourceId) {
			this.resourceId = resourceId;
		}
		public String getCreatorId() {
			return creatorId;
		}
		public void setCreatorId(String creatorId) {
			this.creatorId = creatorId;
		}
		public String getCreatorName() {
			return creatorName;
		}
		public void setCreatorName(String creatorName) {
			this.creatorName = creatorName;
		}
		public String getUpdaterId() {
			return updaterId;
		}
		public void setUpdaterId(String updaterId) {
			this.updaterId = updaterId;
		}
		public String getUpdaterName() {
			return updaterName;
		}
		public void setUpdaterName(String updaterName) {
			this.updaterName = updaterName;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
}