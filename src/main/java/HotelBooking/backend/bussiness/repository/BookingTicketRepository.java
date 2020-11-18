package HotelBooking.backend.bussiness.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import HotelBooking.backend.persistant.BookingTicket;


@Component
public interface BookingTicketRepository extends JpaRepository<BookingTicket, Integer>{
	
//	@Query("SELECT * FROM booking_ticket")
//	public int booking();
	
	@Query(value="SELECT * FROM dbo.getAvailableRoomNumber(?1,?2,?3)", nativeQuery = true)
	public List<Integer> bookingCheck(int roomId, Date checkIn, Date checkOut);
	
	@Query(value="SELECT * FROM booking_ticket WHERE booking_ticket.room_id IN (SELECT rooms_id FROM hotel_rooms WHERE hotel_rooms.hotel_id=?1)", nativeQuery=true)
	public List<BookingTicket> getAllHotelBookingTicket(int hotelId);
}
