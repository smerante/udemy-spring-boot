package com.springboot.web.firstspringbootwebapplication.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.web.firstspringbootwebapplication.models.ToDo;

@Service
public class TodoService {
	private static List<ToDo> todos = new ArrayList<ToDo>();
	private static int todoCount = 3;
/*	static {
		todos.add(new ToDo(1, "Sam", "Learn Spring MVC", new Date(), false));
		todos.add(new ToDo(2, "Sam", "Learn Struts", new Date(), false));
		todos.add(new ToDo(3, "Sam", "Learn Hibernate", new Date(), false));
	} now using data.sql file
*
*
*/
	public List<ToDo> retreiveTodos(String name){
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		List<ToDo> filteredTodos = new ArrayList<ToDo>();
		for(ToDo todo: todos) {
			if(todo.getUser().equals(name))
				filteredTodos.add(todo);
		}
		return filteredTodos;
	}
	public void add(String name, String descr, Date targetDate, boolean isDone) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		todos.add(new ToDo(++todoCount, name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase(),descr,targetDate,isDone));
	}
	public void deleteTodo(int id) {
		for(int i =0; i < todos.size();i++)
			if(todos.get(i).getId()==id)
				todos.remove(i);
	}
	public ToDo getTodo(int id) {
		for(int i =0; i < todos.size();i++)
			if(todos.get(i).getId()==id)
				return todos.get(i);
		return null;
	}
	public void updateTodo(ToDo todo) {
		todos.remove(todo);
		todos.add(todo);
	}
}
