package com.atguigu.atcrowdfunding.project.bean;

public class Project {

	private Integer id,day,supporter,completion,memberid,follower;
	private Long money,supportmoney;
	private String authstatus,name,remark,status,deploydate,createdate,iconpath,imgpath;
	
	public String getAuthstatus() {
		return authstatus;
	}
	public void setAuthstatus(String authstatus) {
		this.authstatus = authstatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getSupporter() {
		return supporter;
	}
	public void setSupporter(Integer supporter) {
		this.supporter = supporter;
	}
	public Integer getCompletion() {
		return completion;
	}
	public void setCompletion(Integer completion) {
		this.completion = completion;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public Integer getFollower() {
		return follower;
	}
	public void setFollower(Integer follower) {
		this.follower = follower;
	}
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	public Long getSupportmoney() {
		return supportmoney;
	}
	public void setSupportmoney(Long supportmoney) {
		this.supportmoney = supportmoney;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeploydate() {
		return deploydate;
	}
	public void setDeploydate(String deploydate) {
		this.deploydate = deploydate;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getIconpath() {
		return iconpath;
	}
	public void setIconpath(String iconpath) {
		this.iconpath = iconpath;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	
}
