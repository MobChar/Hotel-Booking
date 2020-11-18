package HotelBooking.backend.bussiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import HotelBooking.backend.persistant.Category;
import HotelBooking.backend.persistant.PlaceComponent;
import HotelBooking.backend.persistant.PlaceComponentKey;

public interface PlaceRepository  extends JpaRepository<PlaceComponent, PlaceComponentKey>{

}
