package com.info.view.visit.model;

import java.util.Date;

/**
 * 来访管理
 */
public class Visit implements java.io.Serializable {
    	private String visitName; //来访人员
    	private String visitSy; //事由
    	private String visitRs; //人数
    	private Date visitDate; //来访时间
    	private String resourceId; //主键
    	private String creatorId; //创建人ID
    	private String creatorName; //登记人
    	private String updaterId; //修改人ID
    	private String updaterName; //修改人
    	private Date updateTime; //修改时间
    	private Date createTime; //登记时间
	
		public String getVisitName() {
			return visitName;
		}
		public void setVisitName(String visitName) {
			this.visitName = visitName;
		}
		public String getVisitSy() {
			return visitSy;
		}
		public void setVisitSy(String visitSy) {
			this.visitSy = visitSy;
		}
		public String getVisitRs() {
			return visitRs;
		}
		public void setVisitRs(String visitRs) {
			this.visitRs = visitRs;
		}
		public Date getVisitDate() {
			return visitDate;
		}
		public void setVisitDate(Date visitDate) {
			this.visitDate = visitDate;
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