package com.ohms.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Bed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bedId;
	
	@OneToOne(cascade = CascadeType.MERGE,targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User userId;
	
	@ManyToOne(cascade = CascadeType.MERGE,targetEntity = Room.class)
	@JoinColumn(name = "room_id")
	private Room roomId;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public Bed(int bedId, User userId, Room roomId) {
		super();
		this.bedId = bedId;
		this.userId = userId;
		this.roomId = roomId;
	}

	public Bed() {
		super();
		
	}

	@Override
	public String toString() {
		return "Bed [bedId=" + bedId + ", userId=" + userId + ", roomId=" + roomId + "]";
	}

	
	

}
