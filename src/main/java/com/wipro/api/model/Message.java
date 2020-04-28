package com.wipro.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

public class Message {
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	@JsonInclude(Include.NON_NULL)
	@Getter @Setter
	private String messageType;
	
	@JsonInclude(Include.NON_NULL)
	@Getter @Setter
	private String deviceChannel;
	
	@JsonInclude(Include.NON_NULL)
	@Getter @Setter
	private String messageCategory;
}
