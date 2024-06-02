package com.ohms.dto;

import lombok.Data;

@Data
public class SignUpDto {

	private long phone;
	private String username;
	private String email;
    private String password;
    
	@Override
	public String toString() {
		return "User [phone=" + phone + ", username=" + username + ", email=" + email + ", password="
				+ password + "]";
	}
	
	
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
