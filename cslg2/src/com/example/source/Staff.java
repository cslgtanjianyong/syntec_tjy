package com.example.source;

public class Staff {
	private Page page;
	private int id;//id ��ʶ
	private String userId;//�û����
	private String userName;//Ա������
	private String userRegName;//�û���¼��
	private String userPassword;//�û�����
	private String userEail;//�û����䣬�����һ�����
	private String position;//�û����
	private int level;//�û�Ȩ��
	private String userRemark;//�û���ע
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
