package com.info.view.divergence.model;

import java.util.Date;

/**
 * 登记管理
 */
public class Divergence implements java.io.Serializable {
    	private String divergenceName; //登记标题
    	private String divergenceContext; //登记内容
    	private String divergenceType; //登记类型
    	private String resourceId; //主键
    	private String creatorId; //创建人ID
    	private String creatorName; //创建人
    	private String updaterId; //修改人ID
    	private String updaterName; //修改人
    	private Date updateTime; //修改时间
    	private Date createTime; //创建时间
	
		public String getDivergenceName() {
			return divergenceName;
		}
		public void setDivergenceName(String divergenceName) {
			this.divergenceName = divergenceName;
		}
		public String getDivergenceContext() {
			return divergenceContext;
		}
		public void setDivergenceContext(String divergenceContext) {
			this.divergenceContext = divergenceContext;
		}
		public String getDivergenceType() {
			return divergenceType;
		}
		public void setDivergenceType(String divergenceType) {
			this.divergenceType = divergenceType;
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