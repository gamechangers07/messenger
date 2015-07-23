package com.rest.test.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InjectDemoResource {

	
	@GET
	@Path("annotations")
	 public String getParamsUsingAnnotations(@MatrixParam("queryParam") String matrixParam,
			 								 @HeaderParam("CustomHeaderParam") String header,
			 								 @CookieParam("name") String cookieValue){
		 return "Matrix Param- "+ matrixParam+"--CustomHeaderParam --"+header+"--Cookie--"+cookieValue;
	}
	
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
	String path = uriInfo.getAbsolutePath().toString();
	// String mediaType= headers.getLanguage().getCountry();
		return path+"Media Type-";
		
	}
}