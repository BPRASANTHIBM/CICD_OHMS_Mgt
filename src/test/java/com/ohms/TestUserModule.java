package com.ohms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ohms.controller.UserController;
import com.ohms.dto.LoginDto;
import com.ohms.dto.SignUpDto;

@SpringBootTest
class TestUserModule {
	
	@Autowired
	UserController userController;

	@Test
	@DisplayName("Check whether User sign up properly working or Not")
	void testuserSignin() {	
		LoginDto dto = new LoginDto();
		dto.setUsernameOrEmail("Sundar");
		dto.setPassword("Sundar@123");
		assertNotNull(userController.authenticateUserLogin(dto));
			
	}
	
	
	@Test
	@DisplayName("Check whether get All the Room List is Not Null")
	void testgetAllRooms() {	
		 assertNotNull(userController.getAllRooms());	
	}
	
	
	@Test
	@DisplayName("Check whether the bill entity  is Not Null")
	void testgetUserBill() {	
		 assertNotNull(userController.getUserBill(2));	
	}
	
	@Test
	@DisplayName("Check whether the Verified status is Approved ")
	void testgetVerifiedStatus() {	
		 assertEquals("Approved", userController.verifyStatus(1));
	}
	
	
	@Test
	@Deprecated
	@Disabled
	@DisplayName("Check whether the User Registration is Successful or Not")
	void testUserRegistration() {	
		
	  SignUpDto dto = new SignUpDto();
	  dto.setUsername("Surya");
	  dto.setEmail("surya03@gmail.com");
	  dto.setPhone(9865782314L);
	  dto.setPassword("Surya@03");
		 assertEquals( new ResponseEntity<>("User registered successfully", HttpStatus.OK), userController.authenticateUserSignup(dto));
	}
	
	

}
