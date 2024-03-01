package com.info.view.appriseNote.model;

import java.util.Date;

/**
 * 评论信息
 */
public class AppriseNote implements java.io.Serializable {
    	private String noteId; //帖子外键
    	private String noteName; //帖子标题
    	private String appriseNote; //回复内容
    	private String resourceId; //主键
    	private String creatorId; //创建人ID
    	private String creatorName; //回复人
    	private String updaterId; //修改人ID
    	private String updaterName; //修改人
    	private Date updateTime; //修改时间
    	private Date createTime; //回复时间
	
		public String getNoteId() {
			return noteId;
		}
		public void setNoteId(String noteId) {
			this.noteId = noteId;
		}
		public String getNoteName() {
			return noteName;
		}
		public void setNoteName(String noteName) {
			this.noteName = noteName;
		}
		public String getAppriseNote() {
			return appriseNote;
		}
		public void setAppriseNote(String appriseNote) {
			this.appriseNote = appriseNote;
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