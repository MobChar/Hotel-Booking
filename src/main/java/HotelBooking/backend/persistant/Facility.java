package HotelBooking.backend.persistant;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.sun.istack.NotNull;

@Entity
public class Facility {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition = "nvarchar(256)")
	private String name;
//	
//	@NotNull
//	@ManyToMany
//	@JoinColumn(referencedColumnName = "id")
//	private List<Hotel> hotels;
//	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Facility() {
	}
	public Facility(String name) {
		super();
		this.name = name;
	}
	
//	public List<Hotel> getHotels() {
//		return hotels;
//	}
//	public void setHotels(List<Hotel> hotels) {
//		this.hotels = hotels;
//	}
	
	
	
	
}
