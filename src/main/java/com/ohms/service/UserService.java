package com.ohms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ohms.dto.LoginDto;
import com.ohms.dto.SignUpDto;
import com.ohms.entity.Bill;
import com.ohms.entity.Room;
import com.ohms.entity.User;




public interface UserService {
	
	public User loginUser(LoginDto loginDto);
	
	public boolean signUpUser(SignUpDto signUpDto);
	
	public boolean requestBooking(String booking,int userId, MultipartFile idProof,int roomId) throws IOException;
	
	public List<Room> getAllRooms();
	
	public Bill getUserBill(int userId);
	
	public boolean vacateRoom (int userId);
	
	public String verifyStatus(int userId);

}
