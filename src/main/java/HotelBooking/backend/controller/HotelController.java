package HotelBooking.backend.controller;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import HotelBooking.backend.bussiness.JacksonConverter;
import HotelBooking.backend.bussiness.Services;
import HotelBooking.backend.bussiness.DTO.EditHotelDTO;
import HotelBooking.backend.bussiness.DTO.NewHotelDTO;
import HotelBooking.backend.bussiness.DTO.SuggestHotelDTO;
import HotelBooking.backend.bussiness.repository.AccountRepository;
import HotelBooking.backend.bussiness.repository.CategoryRepository;
import HotelBooking.backend.bussiness.repository.FacilityRepository;
import HotelBooking.backend.bussiness.repository.HotelRepository;
import HotelBooking.backend.bussiness.storageServices.CustomWindowStorage;
import HotelBooking.backend.persistant.Account;
import HotelBooking.backend.persistant.Category;
import HotelBooking.backend.persistant.Comment;
import HotelBooking.backend.persistant.Facility;
import HotelBooking.backend.persistant.Hotel;
import HotelBooking.backend.persistant.PlaceComponent;
import HotelBooking.backend.persistant.PlaceComponentKey;
import HotelBooking.backend.persistant.Room;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	public static Logger logger = LoggerFactory.getLogger(HotelController.class);
	public static final int MAX_HOTEL_PER_PAGE=6;
	@Autowired
	Services services;
	@Autowired
	AccountRepository accountRepository;

	@GetMapping("/{hotelId}")
	public String getHotel(@PathVariable(value = "hotelId") Integer id, ModelMap models, Principal principal,
			@CookieValue(value = "checkIn") @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> checkInUn,
			@CookieValue(value = "checkOut") @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> checkOutUn)
			throws Exception {
		Date checkIn;
		Date checkOut;
		if (checkInUn.isEmpty() && checkOutUn.isEmpty()) {
			Date today = new Date();
			Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
			logger.warn("AC "+tomorrow.toString());
			checkIn = tomorrow;
			checkOut = tomorrow;
		} else if (checkInUn.isEmpty() || checkOutUn.isEmpty())
			throw new IllegalArgumentException();
		else {
			checkIn = checkInUn.get();
			checkOut = checkOutUn.get();
		}
		Hotel hotel = services.getHotelRepository().getOne(id);
		List<Room> rooms = hotel.getRooms();
		List<Facility> hotelFacilities = hotel.getFacilities();
		List<Comment> comments=hotel.getComments();
		comments.sort(new Comparator<Comment>() {
			 @Override
			  public int compare(Comment a, Comment b) {  
			    return b.getId()-a.getId();
			   }
		});
		

		models.addAttribute("services", services);
		models.addAttribute("hotel", hotel);
		models.addAttribute("rooms", rooms);
		models.addAttribute("hotelFacilities", hotelFacilities);
		models.addAttribute("previewImages", hotel.getPreviewImages());
		models.addAttribute("checkIn", checkIn);
		models.addAttribute("checkOut", checkOut);
		models.addAttribute("comments", comments);
		return "hotel";
	}

	@GetMapping(params = { "location" })
	public String findHotel(HttpServletRequest request, HttpServletResponse response, ModelMap models,
			Principal principal,
			@RequestParam(value="page",required=true,defaultValue="1") Integer page,
			@RequestParam(value = "facility[]", required = true, defaultValue = "") Integer[] facilityIds,
			@RequestParam(value = "category[]", required = true, defaultValue = "") Integer[] categoryIds,
			@RequestParam(value = "checkIn", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date checkInUn,
			@RequestParam(value = "checkOut", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date checkOutUn,
			@RequestParam(value = "location", required = true) String location,
			@RequestParam(value = "distance", required = true) Float distance) throws Exception {
		
		logger.warn("Fac: "+facilityIds.length);
		logger.warn("Cate: "+categoryIds.length);
		logger.warn("Loc "+location);
		logger.warn("Dis: "+distance);
		java.sql.Date checkIn = new java.sql.Date(checkInUn.getTime());
		java.sql.Date checkOut = new java.sql.Date(checkOutUn.getTime());

		response.addCookie(new Cookie("checkIn", checkIn.toString()));
		response.addCookie(new Cookie("checkOut", checkIn.toString()));

		List<SuggestHotelDTO> hotels = services.getSuggestHotel(location, distance, categoryIds, facilityIds, checkIn,
				checkOut);
		logger.warn("HOTEL SIZE: "+hotels.size());
		if(hotels==null) hotels=new ArrayList();	
		if(page>(int)Math.ceil(hotels.size()*1./MAX_HOTEL_PER_PAGE)||page<0) page=0;
		else page--;
		
	
		models.addAttribute("hotels", hotels.subList(page*MAX_HOTEL_PER_PAGE,(int)Math.min(page*MAX_HOTEL_PER_PAGE+MAX_HOTEL_PER_PAGE,hotels.size())));
		models.addAttribute("locationStr", location);
		models.addAttribute("pageCount", (int)Math.ceil(hotels.size()*1./MAX_HOTEL_PER_PAGE));
		models.addAttribute("currentPage",page+1);
		return "find-hotel";
	}

	@GetMapping(params = { "place" })
	public String getHotelByPlace(HttpServletRequest request, HttpServletResponse response, ModelMap models,
			Principal principal,
			@RequestParam(value="page",required=true,defaultValue="1") Integer page,
			@RequestParam(value = "place", required = true) String place,
			@RequestParam(value = "placeType", required = true) Integer placeType) throws Exception {

		PlaceComponent placeCom = services.getPlaceRepository().getOne(new PlaceComponentKey(place, placeType));
		List<Hotel> hotels = placeCom.getHotels();
		
		if(hotels==null) hotels=new ArrayList();	
		if(page>(int)Math.ceil(hotels.size()*1./MAX_HOTEL_PER_PAGE)||page<0) page=0;
		else page--;
		
		models.addAttribute("hotels", hotels.subList(page*MAX_HOTEL_PER_PAGE,(int)Math.min(page*MAX_HOTEL_PER_PAGE+MAX_HOTEL_PER_PAGE,hotels.size())));
		models.addAttribute("place", placeCom);
		models.addAttribute("pageCount", (int)Math.ceil(hotels.size()*1./MAX_HOTEL_PER_PAGE));
		models.addAttribute("currentPage",page+1);
		models.addAttribute("searchResultCount", hotels.size());
		return "get-hotel-by-place";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = { MediaType.APPLICATION_ATOM_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<String> newHotel(@Valid NewHotelDTO hotelDTO, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
		try {
			services.saveNewHotel(hotelDTO);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}

	}

	@Secured("ROLE_ADMIN")
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = { MediaType.APPLICATION_ATOM_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<String> updateHotel(@Valid EditHotelDTO hotelDTO,BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
		try {
			services.editHotel(hotelDTO);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
	}

	@ModelAttribute("facilities")
	public List<Facility> getFacilities() {
		return services.getAllFacility();
	}

	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return services.getAllCategory();
	}

	@ModelAttribute("account")
	public Account getAccount(Principal principal) throws Exception {
		if (principal != null) {
			logger.warn(principal.toString());
			if (principal instanceof OAuth2AuthenticationToken) {
				OAuth2AuthenticationToken oAuth2Token = (OAuth2AuthenticationToken) principal;
				String email = oAuth2Token.getPrincipal().getAttribute("email");
				return accountRepository.getOne(email);
			} else if (principal instanceof UsernamePasswordAuthenticationToken) {
				UserDetails user = (UserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
				return accountRepository.getOne(user.getUsername());
			} else
				throw new AccessDeniedException("No permission");

		}
		return null;
	}

}
