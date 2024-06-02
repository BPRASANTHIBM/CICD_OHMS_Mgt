package com.ohms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ohms.dto.LoginDto;
import com.ohms.dto.SignUpDto;
import com.ohms.entity.Bill;
import com.ohms.entity.Room;
import com.ohms.entity.User;
import com.ohms.serviceImpl.UserServiceImpl;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
@NoArgsConstructor
public class UserController {
	
	
	/************************************************* 

	* Author: Prasanth_Baskaran - 12119

	* Project_Name: Online Hostel Management System

	* Class: UserController

	************************************************/ 
	

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/signin")
	public ResponseEntity<User> authenticateUserLogin(@RequestBody LoginDto loginDto) {
		System.out.println(loginDto.getUsernameOrEmail());
		User user = userService.loginUser(loginDto);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("signup")
	public ResponseEntity<String> authenticateUserSignup(@RequestBody SignUpDto signUpDto) {
		System.out.println(signUpDto);
		boolean user = userService.signUpUser(signUpDto);
		
			if(user) {
			return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
			}
			else {
			return new ResponseEntity<>("User registerion faild", HttpStatus.CREATED);
			}
		}
	
	
	@PostMapping("/booking")
	public ResponseEntity<String> requestBooking(@RequestParam String booking,@RequestParam int userId,@RequestParam MultipartFile data,@RequestParam int roomId) throws IOException {
		boolean user = userService.requestBooking(booking,userId,data,roomId);
		
			if(user) {
			return new ResponseEntity<>("Room Booking request pending...", HttpStatus.OK);
			}
			else {
			return new ResponseEntity<>("Room Booking request faild", HttpStatus.CREATED);
			}
		}
	
	@GetMapping("/getAllRooms")
	public ResponseEntity<List<Room>> getAllRooms() {
		return new ResponseEntity<>(userService.getAllRooms(), HttpStatus.OK);
		}
	
	@GetMapping("/getBill")
	public Bill getUserBill(@RequestParam int userId) {
		return userService.getUserBill(userId);
	}
	
	@DeleteMapping("/vacateRoom")
	public boolean vacateRoom(@RequestParam int userId) {
		return userService.vacateRoom(userId);
	}
	
	
	@GetMapping("/verifyStatus")
	public String verifyStatus(@RequestParam int userId) {
		return userService.verifyStatus(userId);
	}
	
	
	
	
	}




