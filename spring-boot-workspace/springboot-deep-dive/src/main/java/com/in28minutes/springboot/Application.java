package com.in28minutes.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		for(int i=0;i<500;i++)System.out.print("_");
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		addSpaceForLog();
	}
	
	static void addSpaceForLog() {
		for(int i=0;i<500;i++)System.out.print("_");
		for(int i=0;i<10;i++)System.out.println("");
	}

}