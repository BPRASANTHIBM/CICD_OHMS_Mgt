package com.ohms.dao;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ohms.dto.LoginDto;
import com.ohms.dto.SignUpDto;
import com.ohms.entity.Bed;
import com.ohms.entity.Bill;
import com.ohms.entity.Booking;
import com.ohms.entity.Room;
import com.ohms.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private EntityManager entityManager;

	public User loginUser(LoginDto loginDto) {

		Query query = entityManager
				.createQuery("SELECT u FROM User u WHERE u.email = :usernameOrEmail OR u.username = :usernameOrEmail");

		query.setParameter("usernameOrEmail", loginDto.getUsernameOrEmail());

		User user = (User) query.getSingleResult();

		if (user != null && Objects.equals(user.getPassword(), loginDto.getPassword())) {
			return user;
		}
		return user;
	}

	public boolean signUpUser(SignUpDto signUpDto) {
		if (signUpDto != null) {
			User user = new User();
			user.setUsername(signUpDto.getUsername());
			user.setEmail(signUpDto.getEmail());
			user.setPhone(signUpDto.getPhone());
			user.setPassword(signUpDto.getPassword());
			entityManager.persist(user);
			return true;
		} else {
			return false;
		}

	}

	public boolean bookingRoom(String booking, int userId, MultipartFile idProof, int roomId) throws IOException {
		if (userId > 0 && roomId > 0) {
			try {

				Query query = entityManager.createQuery("select count(*) from Booking b where userId.id = :userId")
						.setParameter("userId", userId);
				System.out.println(query.getSingleResult());
				Long count = (Long) query.getSingleResult();
				boolean isCountOne = count.equals(1L);

				if (isCountOne) {
					return false;
				}

				User user = entityManager.find(User.class, userId);
				Room room = entityManager.find(Room.class, roomId);
				Booking booking1 = new Booking();
				booking1.setIdType(booking);
				booking1.setUserId(user);
				booking1.setRoomId(room);
				booking1.setIdProof(idProof.getBytes());
				booking1.setStatus("pending");
				booking1.setBookingDate(
						LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
								DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
				entityManager.persist(booking1);
				
		     
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		return false;

	}

	public boolean vacateRoom(int userId) {

		if (userId > 0) {
			Query query = entityManager.createQuery("select count(*) from Booking b where userId.id = :userId")
					.setParameter("userId", userId);
			System.out.println(query.getSingleResult());
			Long count = (Long) query.getSingleResult();
			boolean isCountOne = count.equals(1L);

			if (isCountOne) {
				
			

			Bed bed = (Bed) entityManager.createQuery("select b from Bed b where userId.id = :userId")
					.setParameter("userId", userId).getSingleResult();
			entityManager.remove(bed);

			Booking booking = (Booking) entityManager.createQuery("select b from Booking b where userId.id = :userId")
					.setParameter("userId", userId).getSingleResult();
			
		   int roomId=booking.getRoomId().getRoomId();
			entityManager.remove(booking);
			                   
			try {
				Bill bill = (Bill) entityManager.createQuery("select b from Bill b where userId.id = :userId")
						.setParameter("userId", userId).getSingleResult();

				if (bill != null) {
					entityManager.remove(bill);
				} 
			} catch (Exception e) {
			     e.printStackTrace();
			     return false;
			}
		    
		    Long check=(Long) entityManager.createQuery("select count(*) from Bed b where roomId.roomId = :roomId").setParameter("roomId", roomId).getSingleResult();
			System.out.println(check);
			
	       Integer roomCount = (Integer) entityManager.createQuery("select s.roomSharing from Room s where s.roomId = :roomId").setParameter("roomId", roomId).getSingleResult();
	       System.out.println(roomCount);
	       
	       
	       if(!(check >=roomCount)) {
	       Query query1=entityManager.createQuery("UPDATE Room r SET r.roomStatus = :newStatus WHERE r.roomId = :roomId");
	    	  query1.setParameter("newStatus", "Available");
	    	  query1.setParameter("roomId", roomId);
	    	   int flag=query1.executeUpdate();
	    	   System.out.println(flag);
	       }
		   
	   	entityManager.createQuery("UPDATE Room r SET r.memCount = r.memCount - 1 WHERE r.roomId = :roomId").setParameter("roomId", roomId).executeUpdate();
			

			return true;
			}
           return false;
		}

		return false;
	}

	
	public String verifyStatus(int userId) {
		
		if(userId>0) {
			Query query = entityManager.createQuery("select count(*) from Booking b where userId.id = :userId")
					.setParameter("userId", userId);
			System.out.println(query.getSingleResult());
			Long count = (Long) query.getSingleResult();
			boolean isCountOne = count.equals(1L);
			
			if(isCountOne) {
			return (String) entityManager.createQuery("SELECT s.status FROM Booking s WHERE s.userId.id = :userId").setParameter("userId", userId).getSingleResult();
			}
		}
		
		return "notbooking";
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Room> getAllRooms() {
		Query query = entityManager.createQuery("from Room");
		return query.getResultList();
	}

	public Bill getUserBill(int userId) {
		Query query = entityManager.createQuery("select b from Bill b where userId.id = :userId").setParameter("userId",
				userId);
		return (Bill) query.getSingleResult();
	}

}
