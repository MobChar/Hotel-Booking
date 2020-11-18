package HotelBooking.backend.bussiness.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import HotelBooking.backend.bussiness.repository.HotelRepository;
import HotelBooking.backend.persistant.Hotel;

@Component
public class MapperUtility {
	@Autowired
	HotelRepository hotelRepository;
	public Hotel buildHotel(Integer id) {
		return hotelRepository.getOne(id);
	}
}
