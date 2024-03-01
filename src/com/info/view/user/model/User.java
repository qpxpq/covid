package com.info.view.user.model;

import java.util.Date;

/**
 * 用户管理
 */
public class User implements java.io.Serializable {
    	private String userNo; //账号
    	private String password; //密码
    	private String userName; //姓名
    	private String sex; //性别
    	private String mobile; //电话
    	private String address; //地址
    	private String userType; //居民状态
    	private Date oneDate; //第一针
    	private String oneType; //一状态
    	private Integer oneTime; //一间隔
    	private Date twoDate; //第二针
    	private String twoType; //二状态
    	private Integer twoTime; //二间隔
    	private Date threeDate; //第三针
    	private String threeType; //三状态
    	private String resourceId; //主键
    	private String creatorId; //创建人ID
    	private String creatorName; //创建人
    	private String updaterId; //修改人ID
    	private String updaterName; //修改人
    	private Date updateTime; //修改时间
    	private Date createTime; //创建时间
    	


		public String getUserNo() {
			return userNo;
		}
		public void setUserNo(String userNo) {
			this.userNo = userNo;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
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
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
		public Date getOneDate() {
			return oneDate;
		}
		public void setOneDate(Date oneDate) {
			this.oneDate = oneDate;
		}
		public String getOneType() {
			return oneType;
		}
		public void setOneType(String oneType) {
			this.oneType = oneType;
		}
		public Integer getOneTime() {
			return oneTime;
		}
		public void setOneTime(Integer oneTime) {
			this.oneTime = oneTime;
		}
		public Date getTwoDate() {
			return twoDate;
		}
		public void setTwoDate(Date twoDate) {
			this.twoDate = twoDate;
		}
		public String getTwoType() {
			return twoType;
		}
		public void setTwoType(String twoType) {
			this.twoType = twoType;
		}
		public Integer getTwoTime() {
			return twoTime;
		}
		public void setTwoTime(Integer twoTime) {
			this.twoTime = twoTime;
		}
		public Date getThreeDate() {
			return threeDate;
		}
		public void setThreeDate(Date threeDate) {
			this.threeDate = threeDate;
		}
		public String getThreeType() {
			return threeType;
		}
		public void setThreeType(String threeType) {
			this.threeType = threeType;
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