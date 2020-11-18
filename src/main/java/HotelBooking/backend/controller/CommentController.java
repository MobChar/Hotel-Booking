package HotelBooking.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import HotelBooking.backend.bussiness.Services;
import HotelBooking.backend.bussiness.DTO.CommentDTO;
import HotelBooking.backend.persistant.Comment;
import HotelBooking.backend.persistant.Hotel;

@RequestMapping("/comment")
@Controller
public class CommentController {
	@Autowired
	Services services;

	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER_'+#hotelId) or hasRole('ROLE_USER')")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	@PostMapping
	public List<CommentDTO> newComment(@RequestParam("hotelId") Integer hotelId,
			@RequestParam("username") String userName, @RequestParam("comment") String commentStr) throws Exception{
		
		
		Hotel hotel=services.getHotelRepository().getOne(hotelId);
		Comment comment=new Comment(services.getAccountRepository().getOne(userName),commentStr,hotel);
		services.getCommentRepository().save(comment);
		
		List<CommentDTO> re=new ArrayList<CommentDTO>();
		for(Comment com:services.getHotelRepository().getOne(hotelId).getComments()) {
			re.add(new CommentDTO(com.getAccount().getUsername(),com.getAccount().getAvatarPath(),com.getAccount().getFullName(),
					com.getComment()));
		};
		services.getCommentRepository().flush();
		services.getHotelRepository().flush();
		return re;

	}
}
