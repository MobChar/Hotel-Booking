package HotelBooking.backend.bussiness.DTO;

import org.hibernate.validator.constraints.Range;

public class EditHotelDTO extends NewHotelDTO{
	@Range(min = 1, max = 999999999, message = "Id must be positive numbers only")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
