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

import com.wipro.api.model.ErrorMessage;
import com.wipro.api.model.HealthCheck;

@ControllerAdvice
public class ApiExceptionHandler {

	private HttpHeaders headers;
	
	private HealthCheck responseHealthCheck;
	
	private ErrorMessage responseErrorMessage;

	@PostConstruct
	public void init() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		responseHealthCheck = new HealthCheck();
		responseErrorMessage = new ErrorMessage();
	}

	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<HealthCheck> handleDataBaseError(JDBCConnectionException ex) {
		responseHealthCheck.setMessage("Up and running Database Fail");
		return new ResponseEntity<>(responseHealthCheck, headers, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<ErrorMessage> handleValidationError(ValidationException validation) {
		responseErrorMessage.setMessage("Error");
		responseErrorMessage.setErroCode("4004");
		responseErrorMessage.setErrorDetail("Data validation error");
		return new ResponseEntity<>(responseErrorMessage, headers, HttpStatus.PRECONDITION_FAILED);
	}
}
