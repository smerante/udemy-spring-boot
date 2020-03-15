package com.learning.jpa.jpain10steps;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learning.jpa.jpain10steps.entity.User;
import com.learning.jpa.jpain10steps.service.UserDAOService;
import com.learning.jpa.jpain10steps.service.UserRepository;

@Component
public class UserRepoCommandLineRunner implements CommandLineRunner{

	private static final Logger log = 
			LoggerFactory.getLogger(UserRepoCommandLineRunner.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		User user = new User("John", "Admin");
		//New User is created : User [id=1, name=Jack, role=Admin]
		long id = userRepository.save(user).getId();

		Optional<User> userFromId = userRepository.findById(id);
		log.info("New User is created : " + user);
		log.info("New User is found : " + userFromId);
	}
}