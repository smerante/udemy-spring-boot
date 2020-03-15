package com.springboot.web.firstspringbootwebapplication.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//Login => "Hello World" @RequestParam String name, ModelMap model
@Controller
//dont need to store name in session bc of spring security @SessionAttributes("name")
public class WelcomeController {
	
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		model.put("activeTab", "welcome");
		model.put("name", getLoggedinUserName());
		return "welcome";
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
