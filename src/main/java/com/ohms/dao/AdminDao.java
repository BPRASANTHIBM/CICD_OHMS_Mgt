package com.ohms.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;


import com.ohms.dto.AdminLoginDto;
import com.ohms.entity.Admin;
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
public class AdminDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired private JavaMailSender javaMailSender;
	 
    @Value("${spring.mail.username}") private String sender;
	
	public Admin adminUser(AdminLoginDto adminloginDto) {
 
	Query query =entityManager.createQuery("SELECT u FROM Admin u WHERE u.adminEmail = :usernameOrEmail OR u.adminName = :usernameOrEmail");
		
	query.setParameter("usernameOrEmail", adminloginDto.getNameOrEmail());
	
	 Admin admin=  (Admin) query.getSingleResult();
	 
	 if(admin !=null && Objects.equals(admin.getAdminPassword(), adminloginDto.getPassword())) {
		 return admin;
	 }
	return admin;	
	}
	
	
	public boolean addRoomDetails(Room room,int adminId) {	
		if(room != null && adminId>0) {
			Admin admin=entityManager.find(Admin.class, adminId);
			room.setAdminId(admin);
			room.setMemCount(0);
			room.setRoomStatus("Available");
			entityManager.persist(room);
			return true;
		}
		else {
			return false;
		}	
	}
	
	
     public boolean updateBooking(int userId,int roomId,int bookingId) {
		
		if(userId >0 && roomId>0 && bookingId>0) {
			Query query = entityManager.createQuery("UPDATE Booking b SET b.status = :newStatus WHERE b.bookingId = :bookingId");
			query.setParameter("bookingId", bookingId);
			query.setParameter("newStatus", "Approved");
			int addBed = query.executeUpdate();
			
			
			
			if(addBed>0) {
				Room room =entityManager.find(Room.class, roomId);
				User user = entityManager.find(User.class,userId);
				
				Bed bed = new Bed();
				bed.setRoomId(room);
				bed.setUserId(user);
				bed.setStatus("pending");
				entityManager.persist(bed); 
				entityManager.createQuery("UPDATE Room r SET r.memCount = r.memCount + 1 WHERE r.roomId = :roomId").setParameter("roomId", room.getRoomId()).executeUpdate();
				
				String reply=sendMail(user.getEmail(), user.getUsername());
				System.out.println(reply);
			
				
			}
			
			
			   Long check=(Long) entityManager.createQuery("select count(*) from Bed b where roomId.roomId = :roomId").setParameter("roomId", roomId).getSingleResult();
				System.out.println(check);
				
		       Integer roomCount = (Integer) entityManager.createQuery("select s.roomSharing from Room s where s.roomId = :roomId").setParameter("roomId", roomId).getSingleResult();
		       System.out.println(roomCount);
		       
		       
		       if(check >= roomCount) {
		       Query query1=entityManager.createQuery("UPDATE Room r SET r.roomStatus = :newStatus WHERE r.roomId = :roomId");
		    	  query1.setParameter("newStatus", "UnAvailable");
		    	  query1.setParameter("roomId", roomId);
		    	   int flag=query1.executeUpdate();
		    	   System.out.println(flag);
		       }
			
			return true;
		}
		
		return false;
	}
     
     
     
     public String sendMail(String mail,String username) {
 		System.out.println(mail);
 		
		 try {
	            // Creating a simple mail message
	            SimpleMailMessage mailMessage
	                = new SimpleMailMessage();
	 
	            // Setting up necessary details
	            mailMessage.setFrom(sender);
	            mailMessage.setTo(mail);
	            mailMessage.setText("Dear "+username+",\r\n"
	            		+ "\r\n"
	            		+ "We are delighted to inform you that your document submitted through the City Hostels App has been successfully approved, and a bed has been allocated to you."
	            		+ "Your accommodation is now confirmed. Please proceed accordingly.\r\n"
	            		+ "\r\n"
	            		+ "If you have any urgent queries or concerns, feel free to reach out to us.\r\n"
	            		+ "\r\n"
	            		+ "Best regards,\r\n"
	            		+ "\r\n"
	            		+ "City Hostels Admin\r\n"
	            		+ "City Hostels Management Team\r\n"
	            		+"\r\n"
	            		+ "Note:Please be aware that this email is automatically generated by the system, and thus no response is necessary.");
	            mailMessage.setSubject("Document Approval and Bed Allocation Notification");
	 
	            // Sending the mail
	            javaMailSender.send(mailMessage);
	            System.out.println("mail activated");
	            return "Mail Sent Successfully...";
	        }
	 
	        // Catch block to handle the exceptions
	        catch (Exception e) {
	            return "Error while Sending Mail";
	        }
		
	}
     
     
     
     
     
     public boolean generateBill(int userId,int roomId, int bedId) {
    	 
    	 if(userId>0 && roomId >0 && bedId>0) {
    		 System.out.println(userId);
    			Query query = entityManager.createQuery("UPDATE Bed b SET b.status = :newStatus WHERE b.bedId = :bedId");
    			query.setParameter("bedId", bedId);
    			query.setParameter("newStatus", "Generated");
    			int billGen = query.executeUpdate();
    			if(billGen>0) {
    				User user = entityManager.find(User.class,userId);
    				Room room =entityManager.find(Room.class, roomId);
    			    int total;
    			    if(room.getRoomType().equalsIgnoreCase("Ac")) {
    			    	total = 9000;
    			    }
    			    else {
    			    	total = 6000;
    			    }
    				
    				Bill bill = new Bill();
    				bill.setDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    				bill.setTotal(total);
    				bill.setUserId(user);
    				entityManager.persist(bill);  
    			}
    			return true;
    	 }
    	 
    	 return false;
     }
     
     
     
     public Object countRoom() {
    	    Query query= entityManager.createQuery("SELECT COUNT(*) FROM Room");
    	    return query.getSingleResult();
     }
     
     public Object pendingCount() {
    	 Query query = entityManager.createQuery("select count(*) from Booking b where b.status = 'pending'");
    	 return query.getSingleResult();
     }
	
     public Object userStayCount() {
    	 Query query = entityManager.createQuery("select count(*) from Bed");
    	 return query.getSingleResult();
     }

     
     @SuppressWarnings("unchecked")
	public List<Booking>getBookings(){
    	 Query query = entityManager.createQuery("from Booking");
    	 return query.getResultList();
     }
     
     public boolean deleteBooking(int bookingId) {
    	 Booking booking =entityManager.find(Booking.class, bookingId);
    	 if(booking !=null) {
    		 entityManager.remove(booking);
    		 return true;
    	 }
    	 return false;
     }
     
    @SuppressWarnings("unchecked")
	 public List<Bed> getAllBed(){
    	   return entityManager.createQuery("from Bed").getResultList();
     }
}