package HotelBooking.backend.persistant;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

@Entity
public class BookingTicket {
	
	@Id
	@GeneratedValue
	private Integer id;
	private Integer roomNumber;
	
	@NotNull
	private Date checkIn;
	@NotNull
	private Date checkOut;
	@NotNull
	private String idenCardNum;
	@NotNull
	@Column(columnDefinition = "nvarchar(256)")
	private String fullName;
	

	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private Room room;
	
	public BookingTicket() {}



	public BookingTicket(Integer roomNumber, Date checkIn, Date checkOut, String idenCardNum, String fullName,
			 Room room) {
		super();
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.idenCardNum = idenCardNum;
		this.fullName = fullName;
		this.room = room;
	}

	





	public Room getRoom() {
		return room;
	}



	public void setRoom(Room room) {
		this.room = room;
	}
	
	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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



	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	};
	
	
	
	
	
	
	
	
	
}
