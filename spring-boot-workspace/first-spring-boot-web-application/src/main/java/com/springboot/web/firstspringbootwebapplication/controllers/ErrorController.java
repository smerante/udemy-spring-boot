package com.springboot.web.firstspringbootwebapplication.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {

	@ExceptionHandler(Exception.class)
		public ModelAndView handleException(HttpServletRequest request, Exception e, ModelMap model) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("exception",e.getStackTrace());
			mv.addObject("url",request.getRequestURL());
			mv.setViewName("error");
			model.addAttribute("exception", e.getStackTrace());
			model.addAttribute("url", request.getRequestURL());
			return mv;
		}
	
}
