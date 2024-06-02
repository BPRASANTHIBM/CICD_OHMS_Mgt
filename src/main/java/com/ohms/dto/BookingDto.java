package com.ohms.dto;

import java.time.LocalDateTime;

import com.ohms.entity.Room;
import com.ohms.entity.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
	
	
	private int bookingId;
	
     
	private String status;
	private LocalDateTime bookingDate;
    private String idType;


    private byte[] idProof;
	
	
	private User userId;
	
	
	private Room roomId;

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", status=" + status + ", bookingDate=" + bookingDate + ", idType="
				+ idType + ", idProof=" + idProof + ", userId=" + userId + ", roomId=" + roomId + "]";
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public byte[] getIdProof() {
		return idProof;
	}

	public void setIdProof(byte[] idProof) {
		this.idProof = idProof;
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

		
	
	
}
