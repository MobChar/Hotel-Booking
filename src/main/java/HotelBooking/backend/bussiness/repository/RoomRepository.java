package HotelBooking.backend.bussiness.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import HotelBooking.backend.persistant.Category;
import HotelBooking.backend.persistant.Room;

@Component
public interface RoomRepository extends JpaRepository<Room, Integer>{

	@Query(value="SELECT * FROM room r WHERE EXISTS(SELECT hr.rooms_id FROM hotel_rooms hr WHERE hr.rooms_id=r.id AND hr.hotel_id=?1)",nativeQuery=true)
	public List<Room> getAllHotelRoom(Integer hotelId);
	@Query(value="SELECT * FROM dbo.getAvailableRoomNumber(?1,?2,?3)",nativeQuery=true)
	public List<Integer> getAllAvailableRoomNumber(int roomId, Date checkIn, Date checkOut);
}
