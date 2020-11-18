package HotelBooking.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import HotelBooking.backend.bussiness.Services;
import HotelBooking.backend.bussiness.geomatry.Location;
import HotelBooking.backend.bussiness.repository.HotelRepository;
import HotelBooking.backend.persistant.Hotel;

@Controller
public class LocationController {
	@Autowired
	HotelRepository hotelRepository;
	
	@GetMapping("/location")
	public String getLocation() {
		return "google-map";
	}

	@GetMapping("/hotels-map")
	public String getHotelMap(@RequestParam(value="location") String locationStr,@RequestParam(value="hotelIds[]", defaultValue="") List<Integer> ids,ModelMap models,HttpServletRequest request) throws Exception {
		List<Hotel> hotels;
		if(ids.size()>0)
		hotels=hotelRepository.findAllById(ids);
		else hotels=new ArrayList<Hotel>();
		float[] locationArr=Services.parseLocation(locationStr);
		models.addAttribute("hotels",hotels);
		models.addAttribute("location",new Location(locationArr[0],locationArr[1]) );
		return "hotels-map";
	}
}


