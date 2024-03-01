package com.info.view.vaccinum.model;

import java.util.Date;

/**
 * 疫苗信息
 */
public class Vaccinum implements java.io.Serializable {
    	private String vaccinumName; //疫苗名称
    	private String vaccinumContext; //疫苗描述
    	private String vaccinumType; //疫苗针次
    	private String address; //接种地点
    	private Date vaccinumDate; //接种日期
    	private String resourceId; //主键
    	private String creatorId; //创建人ID
    	private String creatorName; //创建人
    	private String updaterId; //修改人ID
    	private String updaterName; //修改人
    	private Date updateTime; //修改时间
    	private Date createTime; //创建时间
	
		public String getVaccinumName() {
			return vaccinumName;
		}
		public void setVaccinumName(String vaccinumName) {
			this.vaccinumName = vaccinumName;
		}
		public String getVaccinumContext() {
			return vaccinumContext;
		}
		public void setVaccinumContext(String vaccinumContext) {
			this.vaccinumContext = vaccinumContext;
		}
		public String getVaccinumType() {
			return vaccinumType;
		}
		public void setVaccinumType(String vaccinumType) {
			this.vaccinumType = vaccinumType;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public Date getVaccinumDate() {
			return vaccinumDate;
		}
		public void setVaccinumDate(Date vaccinumDate) {
			this.vaccinumDate = vaccinumDate;
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