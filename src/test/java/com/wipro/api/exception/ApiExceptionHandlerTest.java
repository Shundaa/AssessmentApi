package com.wipro.api.exception;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

//@RunWith(MockitoJUnitRunner.class)
public class ApiExceptionHandlerTest {

	@InjectMocks
	private ApiExceptionHandler exceptionHandler;

	@Rule
	public ExpectedException exception;

	// @Test
	public void ApiExceptionHandler_CheckUnexpectedExceptionsAreCaughtAndStatusCode503IsReturnedInResponse()
			throws Exception {
		exception.expect(RuntimeException.class);
	}

}
