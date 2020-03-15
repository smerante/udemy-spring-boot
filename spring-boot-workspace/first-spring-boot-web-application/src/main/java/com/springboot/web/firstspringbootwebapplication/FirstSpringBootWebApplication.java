package com.springboot.web.firstspringbootwebapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //@ComponentScan to scan all components not children of this package com.springboot.web.firstspringbootwebapplication
public class FirstSpringBootWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringBootWebApplication.class, args);
	}
}
