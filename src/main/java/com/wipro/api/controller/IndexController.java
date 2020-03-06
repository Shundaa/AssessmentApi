package com.wipro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.api.services.OracleConnectionService;

@RestController
public class IndexController {
	
	@Autowired
	private OracleConnectionService oracleConnection;
	
	@GetMapping("/healthCheck")
	ResponseEntity<String> index() {
		return new ResponseEntity<>("UP and Running", HttpStatus.OK);
	}
	
	@GetMapping("/healthCheck/dataBase")
	ResponseEntity<String> db(){
		return new ResponseEntity<>(oracleConnection.getHealth(),HttpStatus.OK);
	}
}
