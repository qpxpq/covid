package com.info.view.covid.model;

import java.util.Date;

/**
 * 疫情信息
 */
public class Covid implements java.io.Serializable {
    	private String covidName; //标题
    	private String covidContext; //内容
    	private String covidSc; //措施
    	private String covidAddress; //地区
    	private String covidType; //等级
    	private String resourceId; //主键
    	private String creatorId; //创建人ID
    	private String creatorName; //创建人
    	private String updaterId; //修改人ID
    	private String updaterName; //修改人
    	private Date updateTime; //修改时间
    	private Date createTime; //创建时间
	
		public String getCovidName() {
			return covidName;
		}
		public void setCovidName(String covidName) {
			this.covidName = covidName;
		}
		public String getCovidContext() {
			return covidContext;
		}
		public void setCovidContext(String covidContext) {
			this.covidContext = covidContext;
		}
		public String getCovidSc() {
			return covidSc;
		}
		public void setCovidSc(String covidSc) {
			this.covidSc = covidSc;
		}
		public String getCovidAddress() {
			return covidAddress;
		}
		public void setCovidAddress(String covidAddress) {
			this.covidAddress = covidAddress;
		}
		public String getCovidType() {
			return covidType;
		}
		public void setCovidType(String covidType) {
			this.covidType = covidType;
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