package com.ohms.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohms.dto.AdminLoginDto;
import com.ohms.entity.Admin;
import com.ohms.entity.Bed;
import com.ohms.entity.Booking;
import com.ohms.entity.Room;
import com.ohms.serviceImpl.AdminServiceImpl;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
@NoArgsConstructor
public class AdminController {
	
	/************************************************* 

	* Author: Prasanth_Baskaran - 12119

	* Project_Name: Online Hostel Management System

	* Class: AdminController

	************************************************/ 
	
	
	@Autowired
	private AdminServiceImpl adminServiceImpl;

	@PostMapping("/signin")
	public ResponseEntity<Admin> authenticateAdminLogin(@RequestBody AdminLoginDto adminLoginDto) {
		Admin admin = adminServiceImpl.loginAdmin(adminLoginDto);
		if (admin != null) {
			return new ResponseEntity<>(admin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping("/addRoom")
	public ResponseEntity<String> addRoom(@RequestBody Room room,@RequestParam int adminId)
	{     
		boolean admin = adminServiceImpl.addRoomService(room,adminId);
		if (admin) {
			return new ResponseEntity<>("Room Created Successfully !", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Room Creation Failed !", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updateBooking/{userid}/{roomId}/{bookId}")
	public ResponseEntity<Boolean> updateBooking(@PathVariable int userid,@PathVariable int roomId,@PathVariable int bookId ){
	    
		boolean status = adminServiceImpl.updateStatus(userid, roomId, bookId);
		if (status) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/getAllBooking")
	public List<Booking> getAllBooking() {
		return adminServiceImpl.getAllBookingDetails();
	}
	
	@DeleteMapping("/deleteBooking")
	public boolean rejectBooking(@RequestParam int bookingId) {
		return adminServiceImpl.rejectBooking(bookingId);
	}
	
	
	@GetMapping("/roomCount")
	public Object getRoomCount() {
		return adminServiceImpl.roomCount();
	}
	
	@GetMapping("/pendingCount")
	public Object getPendingCount() {
		return adminServiceImpl.pendingCount();
	}
	
	@GetMapping("/userStayCount")
	public Object getStayCount() {
		return adminServiceImpl.userStayCount();
	}
	
	@GetMapping("/getAllBed")
	public List<Bed> getAllBed(){
		return adminServiceImpl.getAllBed();
	}
	
	@PutMapping("/generateBill/{userId}/{roomId}/{bedId}")
	public boolean generateBill(@PathVariable int userId, @PathVariable int roomId, @PathVariable int bedId) {	
		System.out.println(userId);
		return adminServiceImpl.generateBill(userId, roomId,bedId);
	}

}
