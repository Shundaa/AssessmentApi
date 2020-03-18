package com.wipro.api.services;

import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wipro.api.entity.Message;

@Service
public class ValidationService {
	public ResponseEntity<String> validation(Message message) {
		//messageType: String, 4 chars, alphabetic only. Valid values: AReq, ARes, RReq, RRes, PReq, PRes, Erro.
        //deviceChannel: String, 2 chars, numeric only. Valid values: 01, 02 and 03.
        //messageCategory: String, 2 char, numeric only. Valid values: 01 and 02.
		
		if(		  (message.getDeviceChannel()==null
				||Pattern.matches("[0]{1}[1-3]{1}",message.getDeviceChannel()))
				&&(message.getMessageCategory()==null
				||Pattern.matches("[0]{1}[1-2]{1}",message.getMessageCategory()))
				&&(message.getMessageType()==null
				||(Pattern.matches("[ARP]{1}[R]{1}[e]{1}[qs]{1}",message.getMessageType())
				||Pattern.matches("Erro",message.getMessageType())))
				)
			return new ResponseEntity<>("{\"message\":\"Validation ok\"}",HttpStatus.OK);
		
		return  new ResponseEntity<>(
				 "{\"Message\":\"Error\","
				+"\"Error detail\":\"Data validation error\"}",HttpStatus.NOT_ACCEPTABLE);
	}
}
