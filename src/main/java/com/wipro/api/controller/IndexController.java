package com.wipro.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.api.services.OracleConnectionService;

@RestController
public class IndexController {
	
	@Autowired
	private OracleConnectionService oracleConnection;
	
	@RequestMapping("/healthCheck")
	@ResponseBody
	String index() {
		return "UP and Running";
	}
	
	@RequestMapping("/healthCheck/dataBase")
	@ResponseBody
	String db(Map<String, String> model) {
		try {
			return oracleConnection.getConnetcion(model);
		}
		catch (Exception e) {
			return "Fail DATA BASE";
		}
	}

}
