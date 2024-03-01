package com.info.view.antiepidemic.model;

import java.util.Date;

/**
 * 防疫信息
 */
public class Antiepidemic implements java.io.Serializable {
    	private String antiepidemicName; //防疫信息标题
    	private String antiepidemicContext; //防疫信息描述
    	private String antiepidemicYq; //防疫信息要求
    	private String resourceId; //主键
    	private String creatorId; //创建人ID
    	private String creatorName; //创建人
    	private String updaterId; //修改人ID
    	private String updaterName; //修改人
    	private Date updateTime; //修改时间
    	private Date createTime; //创建时间
	
		public String getAntiepidemicName() {
			return antiepidemicName;
		}
		public void setAntiepidemicName(String antiepidemicName) {
			this.antiepidemicName = antiepidemicName;
		}
		public String getAntiepidemicContext() {
			return antiepidemicContext;
		}
		public void setAntiepidemicContext(String antiepidemicContext) {
			this.antiepidemicContext = antiepidemicContext;
		}
		public String getAntiepidemicYq() {
			return antiepidemicYq;
		}
		public void setAntiepidemicYq(String antiepidemicYq) {
			this.antiepidemicYq = antiepidemicYq;
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