package HotelBooking.backend.bussiness.DTO;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SingleBookingDTO {
	@Range(min = 1, max = 999999999, message = "Please select positive numbers only")
	private Integer roomId;
	@Pattern(regexp = "^([0-9]{9})|([0-9]{12})$", message = "Identification Card must contain 9 or 12 number character")
	private String idenCardNum;
	@Size(min=5, max=50, message="Full name must from 5-50 characters")
	private String fullName;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Future(message = "Check in must be in future")
	private Date checkIn;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Future(message = "Check out must be in future")
	private Date checkOut;
	@Range(min = 1, max = 999999999, message = "Please select positive numbers only")
	private Integer roomNumber;
	
	
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getIdenCardNum() {
		return idenCardNum;
	}
	public void setIdenCardNum(String idenCardNum) {
		this.idenCardNum = idenCardNum;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	
	
	
	
}
