package com.ohms.dto;

import java.time.LocalDateTime;

import com.ohms.entity.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
	
	private int billId;
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

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", userId=" + userId + ", total=" + total + ", date=" + date + "]";
	}

}
