package com.in28minutes.springboot.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springboot.Application;
import com.in28minutes.springboot.model.Question;


@RunWith(SpringRunner.class)//Spring Runner runs the test
@SpringBootTest(classes=Application.class,webEnvironment = (SpringBootTest.WebEnvironment.RANDOM_PORT))//SpringBootTest helps launching a spring boot application in a test
public class SurveyControllerIT {

	@LocalServerPort //To find out what port its running on
	private int port;
	HttpHeaders headers = createHettpHeadersWithAuth("user1","abcde");
	@Before
	public void before() {
		headers = createHettpHeadersWithAuth("user1","abcde");
	}
	
	private HttpHeaders createHettpHeadersWithAuth(String userId, String password) {
		HttpHeaders httpHeaders = new HttpHeaders();
		String auth = userId+":"+password;
		byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
		String headerVal = "Basic "+new String(encodedAuth);
		
		httpHeaders.set("Authorization", headerVal);
		return httpHeaders;
	}
	@Test
	public void addQuestionsForSurvey_Test() throws URISyntaxException {
		String url = createUrl("/surveys/Survey1/questions");
		TestRestTemplate restTemplate = new TestRestTemplate();
		headers.set("Content-type", "application/json");
		List<String> options = new ArrayList<String>();
		options.add("1");
		options.add("2");
		options.add("3");
		options.add("4");
		Question question = new Question("4","How Many Tests are there","3",options);
		//By default restTemplate.getForObject(url,String.class) will return as xml
		RequestEntity<Question> requestEntity = new RequestEntity<Question>(question,headers,HttpMethod.POST,new URI(url));
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());
		System.out.println(responseEntity.getHeaders());
		System.out.println(HttpHeaders.LOCATION+":" +responseEntity.getHeaders().getLocation());

        assertTrue(responseEntity.getHeaders().get(HttpHeaders.LOCATION).get(0).contains("/surveys/Survey1/questions/"));
	}
	private String createUrl(String path) {
		return "http://localhost:"+port+""+path;
	}
	@Test 
	public void retrieveQuestionsForSurvey_Test() {
		String url = createUrl("/surveys/Survey1/questions");
		TestRestTemplate restTemplate = new TestRestTemplate();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		//Need to use exchange so you can use entity which has headers
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		//By default restTemplate.getForObject(url,String.class) will return as xml
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);

		
		assertNotNull(responseEntity.getBody());
	}
	@Test
	public void test() {
		String url = createUrl("/surveys/Survey1/questions/Question1");
		TestRestTemplate restTemplate = new TestRestTemplate();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		//Need to use exchange so you can use entity which has headers
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		//By default restTemplate.getForObject(url,String.class) will return as xml
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);

		System.out.println("TEST APP RUNNING ON PORT: "+port);
		System.out.println("______-----OUTPUTT: "+responseEntity.getBody());
		
		assertTrue(responseEntity.getBody().contains("\"id\":\"Question1\""));
	}
	
	@Test
	public void testJsonAssert() {
		String url = createUrl("/surveys/Survey1/questions/Question1");
		TestRestTemplate restTemplate = new TestRestTemplate();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		//Need to use exchange so you can use entity which has headers
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		//By default restTemplate.getForObject(url,String.class) will return as xml
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
		String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"],\"correctAnswer\":\"Russia\"}";
		String actual = responseEntity.getBody();
		
		JSONAssert.assertEquals(expected, actual, true);//False doesnt check for blanks, or missing fields
	}

}
