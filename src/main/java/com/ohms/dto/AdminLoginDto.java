package com.ohms.dto;

import lombok.Data;

@Data
public class AdminLoginDto {
	
private String nameOrEmail;
	
	public String getNameOrEmail() {
		return nameOrEmail;
	}
	public void setNameOrEmail(String nameOrEmail) {
		this.nameOrEmail = nameOrEmail;
	}
	@Override
	public String toString() {
		return "AdminLoginDto [nameOrEmail=" + nameOrEmail + ", password=" + password + "]";
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
	
	public AdminLoginDto() {
		super();

	}

}
