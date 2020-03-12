package com.wipro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

import com.wipro.api.services.ConnectionService;

@RestController
public class IndexController {
	
	public IndexController(){
		gson=new Gson();
	}
	//@Autowired
	private Gson gson;

	@Autowired
	private ConnectionService oracleConnection;

	@GetMapping("/healthCheck")
	public ResponseEntity<String> index() {
		return new ResponseEntity<>(gson.toJson(oracleConnection.getHealth()), HttpStatus.OK);
	}

	@GetMapping("/healthCheck/dataBase")
	public ResponseEntity<String> db() {
		return new ResponseEntity<>((gson.toJson(oracleConnection.getHealthDB())), HttpStatus.OK);
	}
}
