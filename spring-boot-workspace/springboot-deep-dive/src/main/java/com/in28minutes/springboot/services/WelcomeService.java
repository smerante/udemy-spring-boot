package com.in28minutes.springboot.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//Spring to manage this bean and create an instance of this//@Service or @bean
@Component
public class WelcomeService{
	@Value ("${welcome.message}")
	private String welcomeMessage;
	
	public String retrieveWelcomeMessage() {
		//Pretend this is a complex business logic method
		return welcomeMessage;
	}
}