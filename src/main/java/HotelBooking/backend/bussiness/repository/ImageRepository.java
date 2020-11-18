package HotelBooking.backend.bussiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import HotelBooking.backend.persistant.Image;


@Component
public interface ImageRepository extends JpaRepository<Image, Integer>{
}
