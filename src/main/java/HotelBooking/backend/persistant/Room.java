package HotelBooking.backend.persistant;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.sun.istack.NotNull;

import HotelBooking.backend.bussiness.repository.RoomRepository;


@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column(columnDefinition = "nvarchar(256)")
	private String name;
	private float price;
	private int maxRoom;
	@NotNull
	@ManyToOne
	@JoinColumn(name="image_id", referencedColumnName="id")
	private Image image;
	
//	@NotNull
//	@ManyToMany(mappedBy = "room")
//	private List<BookingTicket> bookingTicket;
	
	@NotNull
	@ManyToOne
	private Hotel hotel;
//	
	public Room() {
	}
	
	public Room(String name, float price, int maxRoom, Image image,Hotel hotel) {
		super();
		this.name = name;
		this.price = price;
		this.maxRoom=maxRoom;
//		bookingTicket=new ArrayList<BookingTicket>();
		this.hotel=hotel;
		this.image=image;
//		this.hotel=hotel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	

	public int getMaxRoom() {
		return maxRoom;
	}

	public void setMaxRoom(int maxRoom) {
		this.maxRoom = maxRoom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	

//	public List<BookingTicket> getBookingTicket() {
//		return bookingTicket;
//	}
//
//	public void setBookingTicket(List<BookingTicket> bookingTicket) {
//		this.bookingTicket = bookingTicket;
//	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
	
	
	
	
	
}
