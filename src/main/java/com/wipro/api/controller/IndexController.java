package com.wipro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.api.services.OracleConnectionService;

@RestController
public class IndexController {
	
	@Autowired
	private OracleConnectionService oracleConnection;
	
	@RequestMapping("/healthCheck")
	String index() {
		return "UP and Running";
	}

	@RequestMapping("/healthCheck/dataBase")
	String db() {
		try {
		oracleConnection.getConnetcion();
		}
		catch (Exception e) {
			return "Fail DATA BASE";
		}
		return "UP and Running DATA BASE";
	}

}
