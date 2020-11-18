package HotelBooking.backend.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import HotelBooking.backend.bussiness.DTO.NewBookingDTO;
import HotelBooking.backend.bussiness.repository.BookingTicketRepository;
import HotelBooking.backend.bussiness.repository.RoomRepository;
import HotelBooking.backend.persistant.BookingTicket;
import HotelBooking.backend.persistant.Room;

@Controller
@RequestMapping("/booking")
public class BookingController {

	public static Logger logger = LoggerFactory.getLogger(BookingController.class);
	@Autowired
	BookingTicketRepository bookingTicketRepository;
	@Autowired
	RoomRepository roomRepository;


	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@ResponseBody
	@PostMapping(path = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> newBooking(@Valid NewBookingDTO newBookingBody, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
		try {
			// Check booking is valid
			// Check checkOut >=checkIn
			if (newBookingBody.getCheckIn().getTime() > newBookingBody.getCheckOut().getTime())
				throw new IllegalArgumentException();

			java.sql.Date checkIn = new java.sql.Date(newBookingBody.getCheckIn().getTime());
			java.sql.Date checkOut = new java.sql.Date(newBookingBody.getCheckOut().getTime());

			Room room = roomRepository.getOne(newBookingBody.getRoomId());
			List<Integer> roomNumberList=bookingTicketRepository.bookingCheck(room.getId(), checkIn, checkOut);
			if(roomNumberList.size()< newBookingBody.getNumberOfRoom()) throw new IllegalArgumentException();
			
			
			for(int i=0;i< newBookingBody.getNumberOfRoom();i++) {
				// Create new booking
				BookingTicket bookingTicket = new BookingTicket(roomNumberList.get(i),checkIn, checkOut, newBookingBody.getIdenCardNum(),
						newBookingBody.getFullName(), room);
				bookingTicketRepository.save(bookingTicket);
			}
		
			//Save booking Ticket
			bookingTicketRepository.flush();
			
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
		
		

	}
	

	
}
