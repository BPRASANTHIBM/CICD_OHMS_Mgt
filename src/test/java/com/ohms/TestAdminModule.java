package com.ohms;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.ohms.controller.AdminController;
import com.ohms.dto.AdminLoginDto;
import com.ohms.entity.Bed;
import com.ohms.entity.Booking;

@SpringBootTest
class TestAdminModule {
	
	@Autowired
	AdminController adminController;
	
	
	@Test
	@DisplayName("Check whether admin sign in properly working or Not")
	void testadminSignup() {
		
		AdminLoginDto adminDto = new AdminLoginDto();
		adminDto.setNameOrEmail("Prasanth");
        adminDto.setPassword("Prasanth@123");
        assertNotNull(adminController.authenticateAdminLogin(adminDto));
	}

	@Test
	@DisplayName("Check whether Get all Booking List are Not Null")
	void testgetAllbookigs() {
		List<Booking> getAllBookings = adminController.getAllBooking();
		assertNotNull(getAllBookings);
	}
	
	
	@Test
	@DisplayName("Check whether get all the Room Count are Not Null")
	void testgetRoomCount() {
		Object count = adminController.getRoomCount();
		assertNotNull(count);
	}
	
	
	@Test
	@DisplayName("Check whether get all the Pending Count are Not Null")
	void testgetPendingCount() {
		Object count = adminController.getPendingCount();
		assertNotNull(count);
	}
	
	
	@Test
	@DisplayName("Check whether get all the User Resident Count are Not Null")
	void testgetResidentCount() {
		Object count = adminController.getStayCount();
		assertNotNull(count);
	}
	
	@Test
	@DisplayName("Check whether get all the Bed List are Not Null")
	void testgetBedList() {
		List<Bed>bedList = adminController.getAllBed();
		assertNotNull(bedList);
	}
	
	
	
	

}
