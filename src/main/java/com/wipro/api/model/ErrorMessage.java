package com.wipro.api.model;

import lombok.Getter;
import lombok.Setter;

public class ErrorMessage {
	
	@Setter @Getter
	private String message;
	
	@Setter @Getter
	private String erroCode;
	
	@Setter @Getter
	private String errorDetail;
}
