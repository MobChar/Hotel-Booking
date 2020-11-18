package HotelBooking.backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import HotelBooking.backend.bussiness.DTO.HotelDTO;
import HotelBooking.backend.bussiness.DTO.SuggestHotelDTO;
import HotelBooking.backend.bussiness.geomatry.GeoCoding;
import HotelBooking.backend.bussiness.geomatry.Location;
import HotelBooking.backend.bussiness.repository.HotelRepository;
import HotelBooking.backend.persistant.Hotel;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HotelReposTest {
	@Autowired
	HotelRepository hotelRepository;

	@Test
	public void autoWiredTesting() {
		assertThat(hotelRepository).isNotNull();
	}

	@Test
	public void addTesting() {
		hotelRepository.saveAndFlush(new Hotel());
		assertThat(hotelRepository.getOne(1)).isNotNull();
	}

//	@Test
//	public void testingGeoDistant() {
////		Location a=new Location(46.5610058f, 26.9098054f);
////		Location b=new Location(44.391403f, 26.1157184f);
////		assertThat(Math.abs(GeoCoding.getDistantInMeter(a,b)/1000- 249.3424456)).isLessThan(10);
//		assertThat(hotelRepository.getSampleDistant()).isEqualTo(0);
//	}
	
	@Test
	public void testParseDate() throws ParseException {
		String s="01-01-1999";
		Date checkIn=new SimpleDateFormat("MM-dd-yyyy").parse(s);
		assertThat(checkIn).isNotNull();
	}

//	@Test
//	public void testSuggestHotel() {
//		hotelRepository.saveAndFlush(new Hotel("Name 1", "Address 1", 46.5610058f, 26.9098054f));
//		hotelRepository.saveAndFlush(new Hotel("Name 1", "Address 2", 44.391403f, 26.1157184f));
//		List<HotelRepository.SuggestHotelDTO> hotelLs=hotelRepository.suggestHotel(46.5610058f, 26.9098054f, 300);
//		assertThat(hotelLs.size()).isEqualTo(2);
//		assertThat(hotelLs.get(1).getHotelInfo().getName()).isEqualTo("Name 1");
//	}

}
