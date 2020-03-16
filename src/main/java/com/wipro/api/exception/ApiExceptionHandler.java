package com.wipro.api.exception;

import javax.annotation.PostConstruct;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ApiExceptionHandler {

	private HttpHeaders headers;

	@PostConstruct
	public void init() {
		headers = new HttpHeaders();
	}
	
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<String> handleDataBaseError(JDBCConnectionException ex) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<String>("{\"message\":\"Up and running Database Fail\"}", headers,HttpStatus.SERVICE_UNAVAILABLE);
	}
}
