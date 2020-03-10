package com.wipro.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Gson gson = new Gson();
    
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<String> handleDataBaseError(RuntimeException ex) {
		return new ResponseEntity<String>(gson.toJson("UP and Running Data Base Fail"), HttpStatus.SERVICE_UNAVAILABLE);
	}
}
