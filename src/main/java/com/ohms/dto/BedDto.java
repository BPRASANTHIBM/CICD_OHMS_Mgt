package com.ohms.dto;

import com.ohms.entity.Room;
import com.ohms.entity.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class BedDto {
	
private int bedId;
	

	private User userId;
	
	private Room roomId;
	
	private String status;
	
	public int getBedId() {
		return bedId;
	}

	public void setBedId(int bedId) {
		this.bedId = bedId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Room getRoomId() {
		return roomId;
	}

	public void setRoomId(Room roomId) {
		this.roomId = roomId;
	}

	

	@Override
	public String toString() {
		return "Bed [bedId=" + bedId + ", userId=" + userId + ", roomId=" + roomId + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
