package com.springboot.web.firstspringbootwebapplication.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.web.firstspringbootwebapplication.models.ToDo;

//Lots of functionality present to insert, retrieve, delete, and update todo
public interface TodoRepo extends JpaRepository<ToDo, Integer> {

	//Find by {VarName}
	List<ToDo> findByUser(String User);
}
