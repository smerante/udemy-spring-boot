package com.in28minutes.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.configuration.BasicConfiguration;
import com.in28minutes.springboot.services.WelcomeService;

@RestController
public class WelcomeController {
	//AutoWiring
	//Dependency injection
	@Autowired
	private WelcomeService service;
	
	@Autowired
	private BasicConfiguration configuration;
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String welcome() {
		return service.retrieveWelcomeMessage();
	}
	
	@RequestMapping(value="/dynamic-configuration", method = RequestMethod.GET)
	public Map<String,Object> dynamicConfiguration() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("message", configuration.getMessage());
		map.put("number", configuration.getNumber());
		map.put("value", configuration.isValue());

		return map;
	}
}

