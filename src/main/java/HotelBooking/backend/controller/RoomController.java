package HotelBooking.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import HotelBooking.backend.bussiness.Services;
import HotelBooking.backend.bussiness.DTO.EditRoomDTO;
import HotelBooking.backend.bussiness.DTO.NewRoomDTO;
import HotelBooking.backend.persistant.Room;

@Controller
@RequestMapping("/room")
public class RoomController {
	@Autowired
	private Services services;
	@Secured("ROLE_ADMIN")
	@ResponseBody
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> newHotelRoom(@Valid NewRoomDTO addDTO, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
		try {
			services.saveNewRoom(addDTO);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
	};
	@ResponseBody
	@Secured("ROLE_ADMIN")
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> editHotelRoom(@Valid EditRoomDTO editDTO,BindingResult bindingResult) throws Exception {
			if(bindingResult.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
			try {
				services.editRoom(editDTO);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
			}
			catch(Exception ex) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
			}
	};
}
