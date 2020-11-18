package HotelBooking.backend.controller;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import HotelBooking.backend.bussiness.repository.AccountRepository;
import HotelBooking.backend.persistant.Account;

@Controller
public class LoginController {
	public static Logger logger=LoggerFactory.getLogger(LoginController.class);
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping("/oauth2-successed")
	public String loginSuccessed(Principal principal, ModelMap models) throws Exception{
		if (principal != null) {
			if(principal instanceof OAuth2AuthenticationToken) {
				OAuth2AuthenticationToken oAuth2Token = (OAuth2AuthenticationToken) principal;
				String picturePath=oAuth2Token.getPrincipal().getAttribute("picture");
				String email=oAuth2Token.getPrincipal().getAttribute("email");
				String fullName=oAuth2Token.getPrincipal().getAttribute("name");
				if(accountRepository.findById(email).isEmpty()) {
					accountRepository.save(new Account(email,fullName,picturePath));
					logger.warn("Save to account repository: "+accountRepository.count());
				}
				else accountRepository.getOne(email).setAvatarPath(picturePath);
				return "close";
			}
			else throw new AccessDeniedException("No permission");
		}
		else throw new AccessDeniedException("No permission");
	}
}
