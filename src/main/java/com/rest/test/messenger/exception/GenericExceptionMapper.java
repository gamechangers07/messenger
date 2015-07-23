package com.rest.test.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.rest.test.messenger.model.ErrorMessage;


//PRovider annotation, it registers in JAX-RS to map the exception. Otherwords XML mapping for beans
//@Provider -- Enable it if you need to handle exception for all...
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		ErrorMessage errorMessage= new ErrorMessage(exception.getLocalizedMessage(),500,"http://www.google.com");
		// TODO Auto-generated method stub
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
	}

}
