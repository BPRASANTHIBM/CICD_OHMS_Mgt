package com.ohms.dto;

public class AdminDto {
	
	private int adminId;
	private String adminName;
	private String adminEmail;
	private String adminAddress;
	private long adminPhone;
	private String adminPassword;
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminAddress() {
		return adminAddress;
	}
	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}
	public long getAdminPhone() {
		return adminPhone;
	}
	public void setAdminPhone(long adminPhone) {
		this.adminPhone = adminPhone;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public AdminDto() {
		super();
		
	}
	public AdminDto(int adminId, String adminName, String adminEmail, String adminAddress, long adminPhone,
			String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminAddress = adminAddress;
		this.adminPhone = adminPhone;
		this.adminPassword = adminPassword;
	}
	


}
