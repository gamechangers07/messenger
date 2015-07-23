package com.rest.test.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Path("/")
public class CommentResource {

	
	@GET
	public String test(){
		return "new Sub Resoucre";
	}
	
	// Message id will come parent resource
	@GET
	@Path("/{commentId}")
	public String getCommentById( @PathParam("commentId") long commentId ,@PathParam("messageId") long messageId){
		return "requested comment id " +commentId+ " Message ID "+ messageId;
	}
}
