package com.info.view.apply.model;

import java.util.Date;

/**
 * 核酸检测申请
 */
public class Apply implements java.io.Serializable {
    	private String applyName; //申请标题
    	private String applyContext; //申请内容
    	private String userId; //用户Id
    	private String userName; //申请人
    	private String authorId; //审核人
    	private String authorName; //审核人
    	private String authorContext; //回复内容
    	private String applyType; //状态
    	private String resourceId; //主键
    	private String creatorId; //创建人ID
    	private String creatorName; //创建人
    	private String updaterId; //修改人ID
    	private String updaterName; //修改人
    	private Date updateTime; //修改时间
    	private Date createTime; //创建时间
	
		public String getApplyName() {
			return applyName;
		}
		public void setApplyName(String applyName) {
			this.applyName = applyName;
		}
		public String getApplyContext() {
			return applyContext;
		}
		public void setApplyContext(String applyContext) {
			this.applyContext = applyContext;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getAuthorId() {
			return authorId;
		}
		public void setAuthorId(String authorId) {
			this.authorId = authorId;
		}
		public String getAuthorName() {
			return authorName;
		}
		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}
		public String getAuthorContext() {
			return authorContext;
		}
		public void setAuthorContext(String authorContext) {
			this.authorContext = authorContext;
		}
		public String getApplyType() {
			return applyType;
		}
		public void setApplyType(String applyType) {
			this.applyType = applyType;
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