package com.rest.test.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.rest.test.messenger.model.ErrorMessage;


// PRovider annotation, it registers in JAX-RS to map the exception. Otherwords XML mapping for beans
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage errorMessage= new ErrorMessage(exception.getLocalizedMessage(),786,"http://www.google.com");
		// TODO Auto-generated method stub
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}

}
