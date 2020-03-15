package com.springboot.web.firstspringbootwebapplication.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//Login => "Hello World" @RequestParam String name, ModelMap model
@Controller
//dont need to store name in session bc of spring security @SessionAttributes("name")
public class LogoutController {
	
	@RequestMapping(value ="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null){
			new SecurityContextLogoutHandler().logout(request, response, authentication);;
		}
		return "redirect:/"; //redirects back to WelcomeController.showWelcomePage
	}

	//Get logged in userId and Pass from spring security
	private String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
	}
}
