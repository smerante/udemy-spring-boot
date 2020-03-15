package com.in28minutes.springboot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.model.Question;
import com.in28minutes.springboot.model.Survey;
import com.in28minutes.springboot.services.SurveyService;
/*
 * "/survey/{serveyID}/questions" :
"/surveys/Survey1/questions"
@GetMapping or method = RequestMethod.Get
@PostMapping or method = RequestMethod.POST
 */
@RestController
public class SurveyController {
	
	@Autowired
	private SurveyService surveyService;
	/*
	 * Retrieve all surveys
	 */
	@RequestMapping(value="/surveys", method = RequestMethod.GET)
	public List<Survey> getAllSurveys(){
		return surveyService.retrieveAllSurveys();		
	}
	
	/*
	 * Retrieve Questions from a specific survey
	 */
	@RequestMapping(value="/surveys/{surveyId}/questions", method = RequestMethod.GET)
	public List<Question> retrieveQuestionsForSurvey(@PathVariable String surveyId){
		return surveyService.retrieveQuestions(surveyId);
		
	}
	
	/* Returns 
	 * {"id":"Question1","description":"Largest Country in the World","options":["India","Russia","United States","China"],"correctAnswer":"Russia"}
	 */
	@RequestMapping(value="/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.GET)
	public Question retrieveDetailsForQuestionFromSurvey(@PathVariable String surveyId,@PathVariable String questionId){
		return surveyService.retrieveQuestion(surveyId, questionId);
	}
	
	
	/*
	 * Takes Question body
	 * And returns ResponseHeader indicating URI location
	 */
	@PostMapping(value="/surveys/{surveyId}/questions")
	
	public ResponseEntity<List<Survey>> addQuestionsForSurvey(@PathVariable String surveyId,@RequestBody Question newQuestion){
		//According to standards of a successful add would be to return URI of the new resource in Response Header
		//Status - created resource
		ResponseEntity<List<Survey>> responsEntity;
		HttpHeaders responseHeaders = new HttpHeaders();
		
		Question question =  surveyService.addQuestion(surveyId, newQuestion);
		if(question==null){
			responseHeaders.set("Added Successful", ""+false);
			responsEntity = new ResponseEntity<List<Survey>>(null,responseHeaders,HttpStatus.NOT_ACCEPTABLE);
			return responsEntity;
		}
		//Appends /{id} and replaces it with question ID
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(question.getId()).toUri();
		responseHeaders.setLocation(location);
		responseHeaders.set("Added Successful", ""+true);
		responsEntity = new ResponseEntity<List<Survey>>(surveyService.retrieveAllSurveys(),responseHeaders,HttpStatus.CREATED);
		return responsEntity;
		
	}
}
