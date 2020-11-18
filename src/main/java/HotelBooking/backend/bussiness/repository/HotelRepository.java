package HotelBooking.backend.bussiness.repository;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import HotelBooking.backend.bussiness.DTO.SuggestHotelDTO;
import HotelBooking.backend.persistant.Hotel;

@Component
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	@Query(value = "SELECT dbo.caculateDistantInKiloMeter(5,5,5,5)", nativeQuery = true)
	public float getSampleDistant();

	@Query(value = "SELECT dbo.caculateDistantInKiloMeter( ?1, ?2, h.latitude, h.longitude) as distance, h.id  as id FROM Hotel h "
			+ "WHERE dbo.caculateDistantInKiloMeter( ?1, ?2, h.latitude, h.longitude) <= ?3 "
			+ "AND h.category_id IN ?4 "
			+ "AND (SELECT COUNT(*) FROM hotel_facilities hf  WHERE hf.hotel_id=h.id AND hf.facilities_id IN ?5)=?6 "
			+ "AND EXISTS(SELECT r.id FROM room r WHERE r.hotel_id=h.id AND EXISTS(SELECT * FROM dbo.getAvailableRoomNumber(r.id,?7,?8)))", nativeQuery = true)
	public List<SuggestHotelDTO> suggestHotel(float latitude, float longitude, float maxDistantInKiloMeter,
			Integer[] categoryIds, Integer[] facilityIds,Integer facilitySize , Date checkIn, Date checkOut);
	
	//(hotel_facilities.facilities_id from hotel_facilities  WHERE hotel_facilities.hotel_id=h.id AND hotel_facilities.facilities_id IN ?4)
}
