package HotelBooking.backend.bussiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import HotelBooking.backend.persistant.PlaceType;

public interface PlaceTypeRepository  extends JpaRepository<PlaceType, Integer>{
	@Query(value="SELECT * FROM place_type WHERE name=?1",nativeQuery=true)
	public PlaceType getPlaceTypeByName(String placeTypeName) ;
}

