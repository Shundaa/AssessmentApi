package com.wipro.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	  @RequestMapping("/healthCheck")
	  String index() {
	    return "UP and Running";
	  }
	  @RequestMapping("/healthCheck/dataBase")
	  String db() {
	    return "UP and Running DATA BASE";
	  }
}
