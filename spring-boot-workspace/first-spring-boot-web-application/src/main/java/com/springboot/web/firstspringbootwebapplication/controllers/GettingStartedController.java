package com.springboot.web.firstspringbootwebapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GettingStartedController {

	@RequestMapping(value="/gettingStarted", method = RequestMethod.GET)
	public String GettingStarted(@RequestParam String userId,String password, ModelMap model) {
		model.put("user", userId);
		String masked = password.substring(0, 1);
		for(int i = 1; i < password.length()-1; i++) {
			masked+= "*";
		}
		masked+=password.substring(password.length()-1);
		model.put("pass", masked);
		return "gettingStarted";
	}
}
