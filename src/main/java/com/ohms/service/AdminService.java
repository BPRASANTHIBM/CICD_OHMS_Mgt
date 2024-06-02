package com.ohms.service;

import java.util.List;

import com.ohms.dto.AdminLoginDto;
import com.ohms.entity.Admin;
import com.ohms.entity.Bed;
import com.ohms.entity.Booking;
import com.ohms.entity.Room;


public interface AdminService {
	
	public Admin loginAdmin(AdminLoginDto adminLoginDto);
	
	public boolean addRoomService(Room room, int adminId);
	
	public boolean updateStatus(int userId , int roomId, int bookId);

	public Object roomCount();
	
	public Object pendingCount();
	
	public Object userStayCount();
	
	public List<Booking>getAllBookingDetails();
	public boolean rejectBooking(int bookingId);
	
	public List<Bed>getAllBed();
	
	public boolean generateBill (int userId,int roomId,int bedId);

}
