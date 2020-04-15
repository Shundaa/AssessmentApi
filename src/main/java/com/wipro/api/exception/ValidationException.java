package com.wipro.api.exception;

import java.util.List;
import com.wipro.api.model.ErrorMessageInvalid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9029573666894755314L;

	@Getter @Setter 
	private List<ErrorMessageInvalid> list;
}
