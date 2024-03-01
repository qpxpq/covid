package com.info.view.flow.model;

import java.util.Date;

/**
 * 流动管理
 */
public class Flow implements java.io.Serializable {
    	private String name; //人员名称
    	private String mobile; //联系电话
    	private String address; //联系地址
    	private String hz; //户主名
    	private String hzMobile; //户主手机号
    	private String flowDate; //入住日期
    	private String flowType; //状态
    	private String unitNo; //单元号
    	private String flowContext; //备注
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
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getHz() {
			return hz;
		}
		public void setHz(String hz) {
			this.hz = hz;
		}
		public String getHzMobile() {
			return hzMobile;
		}
		public void setHzMobile(String hzMobile) {
			this.hzMobile = hzMobile;
		}
		public String getFlowDate() {
			return flowDate;
		}
		public void setFlowDate(String flowDate) {
			this.flowDate = flowDate;
		}
		public String getFlowType() {
			return flowType;
		}
		public void setFlowType(String flowType) {
			this.flowType = flowType;
		}
		public String getUnitNo() {
			return unitNo;
		}
		public void setUnitNo(String unitNo) {
			this.unitNo = unitNo;
		}
		public String getFlowContext() {
			return flowContext;
		}
		public void setFlowContext(String flowContext) {
			this.flowContext = flowContext;
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