package com.springboot.web.firstspringbootwebapplication.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.web.firstspringbootwebapplication.models.ToDo;
import com.springboot.web.firstspringbootwebapplication.services.TodoRepo;

@Controller
//Dont need to store in session because of spring seecurity @SessionAttributes("name")
public class TodoController {

	
	@Autowired
	TodoRepo todoRepo;
	
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//Date format - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping(value ="/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		model.put("activeTab", "list-todos");
		String name = getLoggedInUserName(model);
		model.put("todos", todoRepo.findByUser(name));
		return "list-todos";//directs to the JSP file list-todos
	}

	@RequestMapping(value ="/add-todo", method = RequestMethod.GET)
	public String showAddToDo(ModelMap model) {
		model.put("activeTab", "add-todo");
		model.addAttribute("todo", new ToDo(0,getLoggedInUserName(model),"",new Date(),false));
		return "todo";//directs to the JSP file
	}
	@RequestMapping(value ="/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model,@Valid ToDo todo, BindingResult result) {
		if(result.hasErrors()){
			model.addAttribute("todo", new ToDo(0,getLoggedInUserName(model),"",new Date(),false));
			model.addAttribute("errorMessage", "Enter atleast 10 characters!");
			return "todo";
		}
		String name = getLoggedInUserName(model);
		model.put("activeTab", "list-todos");
		todo.setUser(getLoggedInUserName(model));
		todoRepo.save(todo);
		//todoService.add(name, todo.getDescr(), todo.getTargetDate(), false);
		return "redirect:/list-todos";//Redirects to list-todos method showTodos.. rather than just the jsp file
	}
	@RequestMapping(value ="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id, ModelMap model) {
		todoRepo.delete(id);
		model.put("activeTab", "list-todos");
		return "redirect:/list-todos";
	}
	@RequestMapping(value ="/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(ModelMap model,@Valid ToDo todo, BindingResult result) {
		model.addAttribute("todo",todoRepo.findOne(todo.getId()));
		model.put("activeTab", "todo");
		return "todo";
	}
	@RequestMapping(value ="/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid ToDo todo, BindingResult result) {
		String name = getLoggedInUserName(model);
		todo.setUser(name);
		if(result.hasErrors()){
			model.addAttribute("todo", todo);
			model.addAttribute("errorMessage", "Enter atleast 10 characters!");
			return "todo";
		}
		model.put("activeTab", "list-todos");
		todoRepo.save(todo);
		return "redirect:/list-todos";//Redirects to list-todos method showTodos.. rather than just the jsp file
	}
}