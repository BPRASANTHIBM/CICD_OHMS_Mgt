package com.ohms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int billId;
	@OneToOne(cascade = CascadeType.MERGE,targetEntity = User.class)
	@JoinColumn(name = "user_Id")
	private User userId;
	private int total;
	private LocalDateTime date;
	
	
	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Bill(int billId, User userId, int total, LocalDateTime date) {
		super();
		this.billId = billId;
		this.userId = userId;
		this.total = total;
		this.date = date;
	}

	public Bill() {
		super();
			}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", userId=" + userId + ", total=" + total + ", date=" + date + "]";
	}

	
	

}
