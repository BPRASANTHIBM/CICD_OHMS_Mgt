package com.ohms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Attendance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int attendanceId;
	private String present;
	private String absent;
	private LocalDateTime date;
	
	@OneToOne(cascade = CascadeType.MERGE, targetEntity = Admin.class)
	private Admin adminId;
	
	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", present=" + present + ", absent=" + absent + ", date="
				+ date + ", adminId=" + adminId + "]";
	}

	public Attendance() {
		super();
		
	}

	public Attendance(int attendanceId, String present, String absent, LocalDateTime date, Admin adminId) {
		super();
		this.attendanceId = attendanceId;
		this.present = present;
		this.absent = absent;
		this.date = date;
		this.adminId = adminId;
	}

	public int getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	public String getAbsent() {
		return absent;
	}

	public void setAbsent(String absent) {
		this.absent = absent;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Admin getAdminId() {
		return adminId;
	}

	public void setAdminId(Admin adminId) {
		this.adminId = adminId;
	}

}
