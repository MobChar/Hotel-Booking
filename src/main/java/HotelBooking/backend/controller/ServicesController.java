package HotelBooking.backend.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import HotelBooking.backend.bussiness.Services;
import HotelBooking.backend.bussiness.DTO.SuggestHotelDTO;
import HotelBooking.backend.persistant.Category;
import HotelBooking.backend.persistant.Facility;

@Controller
public class ServicesController {
	@Autowired
	private Services services;

//	@GetMapping("/find-hotel")
//	public String findHotel(HttpServletRequest request, HttpServletResponse response, ModelMap models,
//			Principal principal,
//			@RequestParam(value = "facility[]", required = true, defaultValue = "") Integer[] facilityIds,
//			@RequestParam(value = "category[]", required = true, defaultValue = "") Integer[] categoryIds,
//			@RequestParam("checkIn") @DateTimeFormat(pattern="yyyy-MM-dd")  java.util.Date checkInUn,
//			@RequestParam("checkOut") @DateTimeFormat(pattern="yyyy-MM-dd") java.util.Date checkOutUn,
//			@RequestParam("location") String location,
//			@RequestParam("distance") Float distance) throws Exception {
//
//		java.sql.Date checkIn = new java.sql.Date(checkInUn.getTime());
//		java.sql.Date checkOut = new java.sql.Date(checkOutUn.getTime());
//
//		response.addCookie(new Cookie("checkIn", checkIn.toString()));
//		response.addCookie(new Cookie("checkOut", checkIn.toString()));
//
//		List<SuggestHotelDTO> hotels = services.getSuggestHotel(location, distance, categoryIds, facilityIds, checkIn,
//				checkOut);
//
//		models.addAttribute("hotels", hotels);
//
//		return "find-hotel";
//	}

	@ModelAttribute("facilities")
	public List<Facility> getFacilities() {
		return services.getAllFacility();
	}

	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return services.getAllCategory();
	}

	@ModelAttribute("fullName")
	public String getUserFullName(Principal principal) throws Exception {
//		if (principal != null) {
//			try {
//				OAuth2AuthenticationToken oAuth2Token = (OAuth2AuthenticationToken) principal;
////			logger.warn(oAuth2Token.getPrincipal().getAttribute(name));
//				return oAuth2Token.getPrincipal().getName();
//
//			} catch (Exception ex) {
//				throw new AccessDeniedException("No permission");
//			}
//
//		}
		return "Test";
	}

}
