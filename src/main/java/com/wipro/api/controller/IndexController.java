package com.wipro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.api.services.ConnectionService;

@RestController
public class IndexController {
	
	@Autowired
	private ConnectionService oracleConnection;
	
	@GetMapping(value="/health-check",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> index() {
		return new ResponseEntity<>(oracleConnection.getHealth(), HttpStatus.OK);
	}

	@GetMapping(value="/health-check/database",produces = "application/json")
	public ResponseEntity<String> db() {
		return new ResponseEntity<>(oracleConnection.getHealthDB(), HttpStatus.OK);
	}
}
