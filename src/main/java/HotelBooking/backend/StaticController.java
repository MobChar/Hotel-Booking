package HotelBooking.backend;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import HotelBooking.backend.bussiness.DTO.SuggestHotelDTO;
import HotelBooking.backend.bussiness.repository.AccountRepository;
import HotelBooking.backend.bussiness.repository.BookingTicketRepository;
import HotelBooking.backend.bussiness.repository.CategoryRepository;
import HotelBooking.backend.bussiness.repository.FacilityRepository;
import HotelBooking.backend.bussiness.repository.HotelRepository;
import HotelBooking.backend.bussiness.repository.ImageRepository;
import HotelBooking.backend.bussiness.repository.PlaceRepository;
import HotelBooking.backend.bussiness.repository.RoomRepository;
import HotelBooking.backend.bussiness.requestBody.FindHotelBody;
import HotelBooking.backend.persistant.Account;
import HotelBooking.backend.persistant.Category;
import HotelBooking.backend.persistant.Facility;
import HotelBooking.backend.persistant.Hotel;
import HotelBooking.backend.persistant.PlaceComponent;
import HotelBooking.backend.persistant.Room;

@Controller
public class StaticController {
	public static Logger logger = LoggerFactory.getLogger(StaticController.class);

	@Autowired
	HotelRepository hotelRepository;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	FacilityRepository facilityRepository;
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	BookingTicketRepository bookingTicketRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	PlaceRepository placeRepository;

	@GetMapping("/")
	public String home(Principal principal, ModelMap models) throws Exception {
		return "index";
	}

	@ModelAttribute("facilities")
	public List<Facility> getFacilities() {
		return facilityRepository.findAll();
	}

	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}
	
	@ModelAttribute("places")
	public List<PlaceComponent> getPlaces() {
		return placeRepository.findAll();
	}


	@ModelAttribute("account")
	public Account getAccount(Principal principal) throws Exception {
		if (principal != null) {
			logger.warn(principal.toString());
				if(principal instanceof OAuth2AuthenticationToken) {
					OAuth2AuthenticationToken oAuth2Token = (OAuth2AuthenticationToken) principal;
					String email=oAuth2Token.getPrincipal().getAttribute("email");
					return accountRepository.getOne(email);
				}
				else if(principal instanceof UsernamePasswordAuthenticationToken){
					UserDetails user = (UserDetails) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
					return accountRepository.getOne(user.getUsername());
				}
				else throw new AccessDeniedException("No permission");

		}
		return null;
	}


}
