package com.ohms.serviceImpl;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ohms.dao.UserDao;
import com.ohms.dto.LoginDto;
import com.ohms.dto.SignUpDto;
import com.ohms.entity.Bill;
import com.ohms.entity.Room;
import com.ohms.entity.User;
import com.ohms.service.UserService;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao dao;

	@Override
	public User loginUser(LoginDto loginDto) {
		return dao.loginUser(loginDto);
	}

	@Override
	public boolean signUpUser(SignUpDto signUpDto) {
	       return dao.signUpUser(signUpDto);		
	}

	@Override
	public boolean requestBooking(String booking,int userId,MultipartFile idProof,int roomId) throws IOException {
		
		return dao.bookingRoom(booking,userId,idProof, roomId);
	}

	@Override
	public List<Room> getAllRooms() {
		return dao.getAllRooms();
	}

	@Override
	public Bill getUserBill(int userId) {
		
		return dao.getUserBill(userId);
	}

	@Override
	public boolean vacateRoom(int userId) {
		return dao.vacateRoom(userId);
	}

	@Override
	public String verifyStatus(int userId) {
		return dao.verifyStatus(userId);
	}

}
