package com.springboot.web.firstspringbootwebapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration //class for spring to generate bean definitions for security
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	//Create Users - Sam/abcde123
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("Sam").password("asd")
				.roles("USER", "ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login","/h2-console/**").permitAll() //If someone wants to login permit anyone to do that
				.antMatchers("/", "/*todo*/**").access("hasRole('USER')").and() //If they want to how to any todo page allow them else show them login form.
				.formLogin();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	//Create a Login Page bc default is ugly
}
