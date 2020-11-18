package HotelBooking.backend.bussiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import HotelBooking.backend.persistant.Category;

@Component
public interface CategoryRepository  extends JpaRepository<Category, Integer>{

}
