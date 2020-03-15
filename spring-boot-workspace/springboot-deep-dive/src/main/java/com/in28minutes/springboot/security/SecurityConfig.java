package com.in28minutes.springboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/*
 *  <pre>
	 * &#064;Override
	 * protected void configure(AuthenticationManagerBuilder auth) {
	 * 	auth
	 * 	// enable in memory based authentication with a user named
	 * 	// &quot;user&quot; and &quot;admin&quot;
	 * 	.inMemoryAuthentication().withUser(&quot;user&quot;).password(&quot;password&quot;).roles(&quot;USER&quot;).and()
	 * 			.withUser(&quot;admin&quot;).password(&quot;password&quot;).roles(&quot;USER&quot;, &quot;ADMIN&quot;);
	 * }
 * 
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	//Authentication: User --> Roles
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.inMemoryAuthentication().withUser("user1").password("abcde").roles("USER")
		 						.and().withUser("admin1").password("abcde").roles("USER", "ADMIN");
	 }
	 
	 

		//Authorization: Roles --> Access
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {

		 	//Anything with Survey -> role has User
		 	//Anything else needs admin role
	        http.httpBasic().and().authorizeRequests().antMatchers("/surveys/**").hasRole("USER")
	        										  .antMatchers("/users/**").hasRole("USER")
        										  	  .antMatchers("/**").hasRole("ADMIN").and().csrf().disable().headers().frameOptions().disable();
	    }
	 
}
