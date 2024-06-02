package com.ohms.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;
	private String roomType;
	private String roomStatus;
	private int roomSharing;
	private int memCount;
	
	public int getMemCount() {
		return memCount;
	}

	public void setMemCount(int memCount) {
		this.memCount = memCount;
	}

	@ManyToOne(cascade = CascadeType.MERGE,targetEntity = Admin.class)
	@JoinColumn(name="admin_id")
	private Admin adminId;
	
	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public int getRoomSharing() {
		return roomSharing;
	}

	public void setRoomSharing(int roomSharing) {
		this.roomSharing = roomSharing;
	}

	public Admin getAdminId() {
		return adminId;
	}

	public void setAdminId(Admin adminId) {
		this.adminId = adminId;
	}

	public Room() {
		super();
	}

	public Room(int roomId, String roomType, String roomStatus, int roomSharing, Admin adminId) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
		this.roomStatus = roomStatus;
		this.roomSharing = roomSharing;
		this.adminId = adminId;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomType=" + roomType + ", roomStatus=" + roomStatus + ", roomSharing="
				+ roomSharing + ", adminId=" + adminId + "]";
	}

}
