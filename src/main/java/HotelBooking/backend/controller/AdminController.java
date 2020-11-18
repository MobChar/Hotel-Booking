package HotelBooking.backend.controller;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import HotelBooking.backend.bussiness.Services;
import HotelBooking.backend.bussiness.DTO.EditBookingTicketDTO;
import HotelBooking.backend.bussiness.DTO.NewBookingDTO;
import HotelBooking.backend.bussiness.DTO.SingleBookingDTO;
import HotelBooking.backend.bussiness.repository.CategoryRepository;
import HotelBooking.backend.bussiness.repository.FacilityRepository;
import HotelBooking.backend.bussiness.repository.HotelRepository;
import HotelBooking.backend.persistant.Account;
import HotelBooking.backend.persistant.BookingTicket;
import HotelBooking.backend.persistant.Category;
import HotelBooking.backend.persistant.Facility;
import HotelBooking.backend.persistant.Hotel;
import HotelBooking.backend.persistant.Image;
import HotelBooking.backend.persistant.ManagerAccount;
import HotelBooking.backend.persistant.PlaceType;
import HotelBooking.backend.persistant.Room;

@Controller
@RequestMapping("/admin")
public class AdminController {
	public static Logger logger=LoggerFactory.getLogger(AdminController.class);
	@Autowired
	Services services;
	@GetMapping
	public String getAdminLoginPage(ModelMap model,@RequestParam(value="error",defaultValue="false",required=true) boolean error) {
		model.addAttribute("error", error);
		return "admin/login-form";
	};
	
	@GetMapping("/dashboard")
	public String getAdminDashboard(Principal principal) throws Exception{
		if(principal instanceof UsernamePasswordAuthenticationToken){
			UserDetails user = (UserDetails) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
			Account userAcccount=services.getAccountRepository().getOne(user.getUsername());
			if(userAcccount.getRole().equals("ADMIN")) return "admin/dashboard";
			else if(userAcccount.getRole().contains("MANAGER_")) {
				ManagerAccount manAccount=services.getAccountRepository().getManagerAccount(user.getUsername());
				if(manAccount!=null)
				return "redirect:booking-ticket"+"?hotelId="+manAccount.getHotel().getId();
				else throw new AccessDeniedException("403 returned");
			}
			else throw new AccessDeniedException("403 returned");
		}
		else throw new AccessDeniedException("403 returned");
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER_'+#hotelId)")
	@ResponseBody
	@PostMapping("/available-room")
	public  Map<Integer, List<Integer>> getAvailableRoom(@RequestParam(value="hotelId", required=true) Integer hotelId,
			@RequestParam("checkIn")@DateTimeFormat(pattern="yyyy-MM-dd") Date checkInUntil,
			@RequestParam("checkOut")@DateTimeFormat(pattern="yyyy-MM-dd") Date checkOutUntil) throws Exception{
		java.sql.Date checkIn = new java.sql.Date(checkInUntil.getTime());
		java.sql.Date checkOut = new java.sql.Date(checkOutUntil.getTime());
		
		return services.getAllHotelAvailableRoom(hotelId, checkIn, checkOut);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER_'+#hotelId)")
	@GetMapping("/booking-ticket")
	public String getBookingTicket(@RequestParam(value="hotelId",required=true) Integer hotelId, ModelMap model) throws Exception
	{
		List<BookingTicket> tickets=services.getAllHotelBooking(hotelId);
		model.addAttribute("tickets",tickets);
		model.addAttribute("hotelId",hotelId);
		model.addAttribute("rooms",services.getAllHotelRoom(hotelId));
		return "admin/booking-ticket";
	}
	
	
	@ResponseBody
	@PostMapping(path = "/booking-ticket")
	public ResponseEntity<String> newBooking(Principal principal,@Valid SingleBookingDTO singleBookingDTO,BindingResult bindingResult) throws Exception {

		// Check booking is valid
		// Check checkOut >=checkIn
		
		if(bindingResult.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
		try {
			
			
			java.sql.Date checkIn = new java.sql.Date(singleBookingDTO.getCheckIn().getTime());
			java.sql.Date checkOut = new java.sql.Date(singleBookingDTO.getCheckOut().getTime());
			
			Room room=services.getRoomRepository().getOne(singleBookingDTO.getRoomId());
			int hotelId=room.getHotel().getId();
			
			if(principal instanceof UsernamePasswordAuthenticationToken){
				UserDetails user = (UserDetails) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
				Account userAcccount=services.getAccountRepository().getOne(user.getUsername());
			if(!(userAcccount.getRole().equals("ADMIN")||userAcccount.getRole().equals("MANAGER_"+hotelId)))
				throw new AccessDeniedException("403 returned");
			}
			else throw new AccessDeniedException("403 returned");
			
			services.bookingSingleTicket(singleBookingDTO.getRoomId(),singleBookingDTO.getFullName(),singleBookingDTO.getIdenCardNum()
					, checkIn, checkOut, singleBookingDTO.getRoomNumber());
			
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
			
			
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
		

		
	}
	
	
	@ResponseBody
	@DeleteMapping(path = "/booking-ticket", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> deleteBooking(Principal principal,@RequestParam Integer bookingTicketId,BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
		try {
			Room room=services.getBookingTicketRepository().getOne(bookingTicketId).getRoom();
			int hotelId=room.getHotel().getId();
			if(principal instanceof UsernamePasswordAuthenticationToken){
				UserDetails user = (UserDetails) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
				Account userAcccount=services.getAccountRepository().getOne(user.getUsername());
			if(!(userAcccount.getRole().equals("ADMIN")||userAcccount.getRole().equals("MANAGER_"+hotelId)))
				throw new AccessDeniedException("403 returned");
			}
			else throw new AccessDeniedException("403 returned");
			
			services.getBookingTicketRepository().deleteById(bookingTicketId);
			services.getBookingTicketRepository().flush();
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
		
	}

	
	@Secured("ROLE_ADMIN")
	@ResponseBody
	@PostMapping(path = "/previewImage")
	public ResponseEntity<String> newPreviewImage(@RequestParam MultipartFile previewImage,@RequestParam(required=true) Integer hotelId) throws Exception {
		try {
			Hotel hotel=services.getHotelRepository().getOne(hotelId);
			Image image=services.saveImage(previewImage);
			hotel.getPreviewImages().add(image);
			services.getHotelRepository().flush();
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
		
	}
	
	@Secured("ROLE_ADMIN")
	@ResponseBody
	@DeleteMapping(path = "/previewImage")
	public ResponseEntity<String> deletePreviewImage(@RequestParam Integer hotelId,@RequestParam(required=true) Integer imageId) throws Exception {
		try {
			Hotel hotel=services.getHotelRepository().getOne(hotelId);
			hotel.getPreviewImages().remove(services.getImageRepository().getOne(imageId));
			services.getHotelRepository().flush();
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
		
	}
	
	
	
	
	
	
	@ModelAttribute("hotels")
	public List<Hotel> getHotel() {
		return services.getAllHotel();
	}
	
	
	@ModelAttribute("rooms")
	public Map<Integer,List<Room>> getHotelRoom() {
		Map<Integer,List<Room>> map=new HashMap<Integer,List<Room>>();
		List<Hotel> hotelLs=services.getAllHotel();
		for(Hotel  hotel : hotelLs) {
			map.put(hotel.getId(), hotel.getRooms());
			logger.warn("Size: "+hotel.getRooms().size());
		}
		return map;
	}
	
	@ModelAttribute("facilities")
	public List<Facility> getFacilities() {
		return services.getAllFacility();
	}

	@ModelAttribute("placeType")
	public List<PlaceType> getPlaceType() {
		return services.getPlaceTypeRepository().findAll();
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return services.getAllCategory();
	}
	
	@ModelAttribute("account")
	public Account getAccount(Principal principal) throws Exception {
		if (principal != null) {
			logger.warn(principal.toString());
				if(principal instanceof OAuth2AuthenticationToken) {
					OAuth2AuthenticationToken oAuth2Token = (OAuth2AuthenticationToken) principal;
					String email=oAuth2Token.getPrincipal().getAttribute("email");
					return services.getAccountRepository().getOne(email);
				}
				else if(principal instanceof UsernamePasswordAuthenticationToken){
					UserDetails user = (UserDetails) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
					return services.getAccountRepository().getOne(user.getUsername());
				}
				else throw new AccessDeniedException("No permission");

		}
		return null;
	}
}
