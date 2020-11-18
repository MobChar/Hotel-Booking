package HotelBooking.backend.persistant;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class  ManagerAccount  extends Account{
	@ManyToOne
	@JoinColumn(name = "hotel", referencedColumnName = "id")
	private Hotel hotel;
	
	public ManagerAccount() {};
	public ManagerAccount(String username, String password,String fullName, String avatarPath, Hotel hotel) {
		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setRole("MANAGER");
		this.setAvatarPath(avatarPath);
		this.setFullName(fullName);
		this.setEnable(true);
		this.hotel=hotel;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
	
	
	
}
