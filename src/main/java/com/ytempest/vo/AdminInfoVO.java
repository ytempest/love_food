package com.ytempest.vo;

public class AdminInfoVO {
	private Long adminId;
	private String adminAccount;
	private String adminPwd;
	private String adminName;
	private String adminSex;
	private String adminPhone;
	private String adminEmail;
	public AdminInfoVO() {
		super();
	}
	public AdminInfoVO(Long adminId) {
		super();
		this.adminId = adminId;
	}
	public AdminInfoVO(Long adminId, String adminAccount, String adminPwd,
			String adminName, String adminSex, String adminPhone,
			String adminEmail) {
		super();
		this.adminId = adminId;
		this.adminAccount = adminAccount;
		this.adminPwd = adminPwd;
		this.adminName = adminName;
		this.adminSex = adminSex;
		this.adminPhone = adminPhone;
		this.adminEmail = adminEmail;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public String getAdminAccount() {
		return adminAccount;
	}
	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminSex() {
		return adminSex;
	}
	public void setAdminSex(String adminSex) {
		this.adminSex = adminSex;
	}
	public String getAdminPhone() {
		return adminPhone;
	}
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	@Override
	public String toString() {
		return "AdminInfoVO [adminId=" + adminId + ", adminAccount="
				+ adminAccount + ", adminPwd=" + adminPwd + ", adminName="
				+ adminName + ", adminSex=" + adminSex + ", adminPhone="
				+ adminPhone + ", adminEmail=" + adminEmail + "]";
	}

}