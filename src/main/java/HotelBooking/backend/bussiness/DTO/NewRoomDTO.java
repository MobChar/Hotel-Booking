package HotelBooking.backend.bussiness.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NewRoomDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Range(min = 1, max = 999999999, message = "Please select positive numbers only")
	private Integer hotelId;
	@Size(min=5, max=256 , message = "Room name must contain 5-256 characters")
	private String roomName;
	@Range(min=1, max=10000, message = "Price must in range 1-10000")
	@NotNull
	private Float roomPrice;
	@Range(min = 1, max = 999999999, message = "Please select positive numbers only")
	private Integer maxRoom;
	
	private MultipartFile thumbImage;

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public float getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(float roomPrice) {
		this.roomPrice = roomPrice;
	}

	public int getMaxRoom() {
		return maxRoom;
	}

	public void setMaxRoom(int maxRoom) {
		this.maxRoom = maxRoom;
	}

	public MultipartFile getThumbImage() {
		return thumbImage;
	}

	public void setThumbImage(MultipartFile thumbImage) {
		this.thumbImage = thumbImage;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public void setRoomPrice(Float roomPrice) {
		this.roomPrice = roomPrice;
	}

	public void setMaxRoom(Integer maxRoom) {
		this.maxRoom = maxRoom;
	}
	
	

	
}