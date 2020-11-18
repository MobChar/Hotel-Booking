package HotelBooking.backend.bussiness.DTO;

import org.springframework.beans.factory.annotation.Value;

import HotelBooking.backend.persistant.Hotel;

public interface SuggestHotelDTO {
	public float getDistance();
	
	@Value("#{@mapperUtility.buildHotel(target.id)}")
	public Hotel getHotelInfo();
	
}
