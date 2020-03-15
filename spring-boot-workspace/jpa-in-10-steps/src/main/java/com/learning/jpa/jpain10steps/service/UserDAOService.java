package com.learning.jpa.jpain10steps.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.learning.jpa.jpain10steps.entity.User;
/*
 * Helps us story entitys to the databased called a repository in spring
 */

@Repository
@Transactional
public class UserDAOService {
		
	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(User user){
		entityManager.persist(user);
		return user.getId();
	}
	
	/*
	 * 
	 */
}