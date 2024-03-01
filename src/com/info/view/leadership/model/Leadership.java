package com.info.view.leadership.model;

import java.util.Date;

/**
 * 上传功能
 */
public class Leadership implements java.io.Serializable {
    	private String healthStatus; //健康状态
    	private String tw; //体温
    	private String healthContext; //描述
    	private String fxdq; //风险地区
    	private String jtfs; //交通方式
    	private String info; //其他信息
    	private String userId; //用户Id
    	private String userName; //用户名称
    	private String authorId; //审核人
    	private String authorName; //审核人
    	private String authorContext; //回复内容
    	private String healthType; //状态
    	private String resourceId; //主键
    	private String creatorId; //创建人ID
    	private String creatorName; //创建人
    	private String updaterId; //修改人ID
    	private String updaterName; //修改人
    	private Date updateTime; //修改时间
    	private Date createTime; //创建时间
	
		public String getHealthStatus() {
			return healthStatus;
		}
		public void setHealthStatus(String healthStatus) {
			this.healthStatus = healthStatus;
		}
		public String getTw() {
			return tw;
		}
		public void setTw(String tw) {
			this.tw = tw;
		}
		public String getHealthContext() {
			return healthContext;
		}
		public void setHealthContext(String healthContext) {
			this.healthContext = healthContext;
		}
		public String getFxdq() {
			return fxdq;
		}
		public void setFxdq(String fxdq) {
			this.fxdq = fxdq;
		}
		public String getJtfs() {
			return jtfs;
		}
		public void setJtfs(String jtfs) {
			this.jtfs = jtfs;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
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
		public String getHealthType() {
			return healthType;
		}
		public void setHealthType(String healthType) {
			this.healthType = healthType;
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