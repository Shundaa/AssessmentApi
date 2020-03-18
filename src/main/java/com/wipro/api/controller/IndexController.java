package com.wipro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.api.entity.Message;
import com.wipro.api.services.ConnectionService;
import com.wipro.api.services.ValidationService;

@RestController
public class IndexController {
	
	@Autowired
	private ConnectionService oracleConnection;
	
	@Autowired
	private ValidationService validationService;
	
	@GetMapping(value="/health-check",produces = "application/json")
	public ResponseEntity<String> index() {
		return new ResponseEntity<>(oracleConnection.getHealth(), HttpStatus.OK);
	}

	@GetMapping(value="/health-check/database",produces = "application/json")
	public ResponseEntity<String> db() {
		return new ResponseEntity<>(oracleConnection.getHealthDB(), HttpStatus.OK);
	}
	@PostMapping(value="/auth",produces = "application/json",consumes="application/json")
	public ResponseEntity<String> auth(
			@RequestBody Message message){
		return validationService.validation(message);
	}
}
