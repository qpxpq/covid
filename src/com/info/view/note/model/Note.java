package com.info.view.note.model;

import java.util.Date;

/**
 * 帖子管理
 */
public class Note implements java.io.Serializable {
    	private String noteName; //帖子标题
    	private String noteContext; //帖子内容
    	private String resourceId; //主键
    	private String creatorId; //创建人ID
    	private String creatorName; //创建人
    	private String updaterId; //修改人ID
    	private String updaterName; //修改人
    	private Date updateTime; //修改时间
    	private Date createTime; //创建时间
	
		public String getNoteName() {
			return noteName;
		}
		public void setNoteName(String noteName) {
			this.noteName = noteName;
		}
		public String getNoteContext() {
			return noteContext;
		}
		public void setNoteContext(String noteContext) {
			this.noteContext = noteContext;
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