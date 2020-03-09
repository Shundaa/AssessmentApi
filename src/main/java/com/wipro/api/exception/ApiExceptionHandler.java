package com.wipro.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<String> handleDataBaseError(RuntimeException ex) {
		return new ResponseEntity<String>("UP and Running Data Base Fail", HttpStatus.SERVICE_UNAVAILABLE);
	}
}
