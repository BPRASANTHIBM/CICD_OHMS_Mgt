package com.ohms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohms.dao.AdminDao;
import com.ohms.dto.AdminLoginDto;
import com.ohms.entity.Admin;
import com.ohms.entity.Bed;
import com.ohms.entity.Booking;
import com.ohms.entity.Room;
import com.ohms.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminDao dao;
	
	@Override
	public Admin loginAdmin(AdminLoginDto adminLoginDto) {  
		return dao.adminUser(adminLoginDto);
	}

	@Override
	public boolean addRoomService(Room room,int adminId) {	
		return dao.addRoomDetails(room,adminId);
	}

	@Override
	public boolean updateStatus(int userId, int roomId, int bookId) {
		return dao.updateBooking(userId, roomId, bookId);
		
	}

	@Override
	public Object roomCount() {
		return dao.countRoom();
	}

	@Override
	public Object pendingCount() {
		
		return dao.pendingCount();
	}

	@Override
	public Object userStayCount() {
		return dao.userStayCount();
	}

	@Override
	public List<Booking> getAllBookingDetails() {
		
		return dao.getBookings();
	}

	@Override
	public boolean rejectBooking(int bookingId) {
		return dao.deleteBooking(bookingId);
	}

	@Override
	public List<Bed> getAllBed() {
		return dao.getAllBed();
	}

	@Override
	public boolean generateBill(int userId, int roomId,int bedId) {
		return dao.generateBill(userId, roomId,bedId);
	}
	
	
	
	

}
