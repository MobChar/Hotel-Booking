package HotelBooking.backend.bussiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import HotelBooking.backend.persistant.Account;
import HotelBooking.backend.persistant.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
