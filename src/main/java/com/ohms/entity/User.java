package com.ohms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long phone;
	
	@Column(unique = true)
	private String username;
	
	@Column(unique = true)
	private String email;
	
    private String password;
    
	@Override
	public String toString() {
		return "User [id=" + id + ", phone=" + phone + ", username=" + username + ", email=" + email + ", password="
				+ password + "]";
	}
	public User(int id, long phone, String username, String email, String password) {
		super();
		this.id = id;
		this.phone = phone;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	public User() {
		super();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
