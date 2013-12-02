package com.example.source;

public class Staff {
	private Page page;
	private int id;//id 标识
	private String userId;//用户编号
	private String userName;//员工姓名
	private String userRegName;//用户登录名
	private String userPassword;//用户密码
	private String userEail;//用户邮箱，用于找回密码
	private String position;//用户身份
	private int level;//用户权限
	private String userRemark;//用户备注
	public Staff()
	{
		
	}
	public Staff(String userId, String userName, String userRegName,
			String userPassword, String userEail, String position, int level,
			String userRemark) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userRegName = userRegName;
		this.userPassword = userPassword;
		this.userEail = userEail;
		this.position = position;
		this.level = level;
		this.userRemark = userRemark;
	}
	public Staff(int id, String userId, String userName, String userRegName,
			String userPassword, String userEail, String position, int level,
			String userRemark) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.userRegName = userRegName;
		this.userPassword = userPassword;
		this.userEail = userEail;
		this.position = position;
		this.level = level;
		this.userRemark = userRemark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getUserRegName() {
		return userRegName;
	}
	public void setUserRegName(String userRegName) {
		this.userRegName = userRegName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEail() {
		return userEail;
	}
	public void setUserEail(String userEail) {
		this.userEail = userEail;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
}
