package com.ohms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
    @Column(columnDefinition = "varchar(255) default 'pending'")
	private String status;
	private LocalDateTime bookingDate;
    private String idType;
    
    @Lob
	@Column(name = "idProof", length = 100000)
    private byte[] idProof;
	
	@OneToOne(cascade = CascadeType.MERGE,targetEntity = User.class)
	@JoinColumn(name="user_id")
	private User userId;
	
	@ManyToOne(cascade = CascadeType.MERGE,targetEntity = Room.class )
	@JoinColumn(name="room_Id")
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

		
	
	public Booking(int bookingId, String status, LocalDateTime bookingDate, String idType, byte[] idProof, User userId,
			Room roomId) {
		super();
		this.bookingId = bookingId;
		this.status = status;
		this.bookingDate = bookingDate;
		this.idType = idType;
		this.idProof = idProof;
		this.userId = userId;
		this.roomId = roomId;
	}

	public Booking() {
		super();
		
	}
	
	
}
