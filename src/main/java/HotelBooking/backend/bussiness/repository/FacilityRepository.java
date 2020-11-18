package HotelBooking.backend.bussiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import HotelBooking.backend.persistant.Facility;

@Component
public interface FacilityRepository  extends JpaRepository<Facility, Integer>{

}
