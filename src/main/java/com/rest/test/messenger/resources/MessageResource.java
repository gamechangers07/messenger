package com.rest.test.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.rest.test.messenger.exception.DataNotFoundException;
import com.rest.test.messenger.model.Message;
import com.rest.test.messenger.model.service.MessageService;
import com.sun.jersey.spi.template.ResolvedViewable;

/**
 * This is the rest API class
 * @author pbalwani
 *
 */
@Path("/messages")
// we can add consumes & produces annotation
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getAllMessages(@QueryParam("year") int year,
									    @QueryParam("size") int size, 
									    @QueryParam("start") int start) {
		// return "Hello! This is the get method of RestFull Service API";
		if (year > 0) {
			return messageService.getAllMessagesForYear(year);
		}
		if (start >= 0 && size > 0) {
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}
	
	
//	@GET
//	@Path("getFilteredMessages")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
//		// return "Hello! This is the get method of RestFull Service API";
//		if (filterBean.getYear() > 0) {
//			return messageService.getAllMessagesForYear(filterBean.getYear());
//		}
//		if (filterBean.getStart() >= 0 && filterBean.getSize() >= 0) {
//			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
//		}
//		return messageService.getAllMessages();
//	}
	
	
	
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo){
		Message message=messageService.getMessage(id);
		if(message==null){
			throw new DataNotFoundException("Data No found for message id-"+id);
		}
		message.addLinks(uriInfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(message.getId())).build("").toString(), "self");
		message.addLinks(getUriForProfile(uriInfo,message), "profile");
		message.addLinks(getUriForComments(uriInfo,message), "comments");
		
		return message;
	}
	
	private String getUriForProfile(UriInfo uriInfo,Message message){
		URI uri= uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build("");
		return uri.toString(); 
		
	}
	
	private String getUriForComments(UriInfo uriInfo,Message message){
		URI uri= uriInfo.getBaseUriBuilder().path(MessageResource.class).path(MessageResource.class, "getComments")
				.path(CommentResource.class).build("");
		
		//use resolveTemplates to replace messageId as part of URL @Path("/{messageId}/comments")
		
		return uri.toString(); 
		
	}

//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Message addMessages(Message message){
//		return messageService.addMessage(message);
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessages(Message message, @Context UriInfo uriInfo) {
		Message newMessage=messageService.addMessage(message);
		//return Response.status(Status.CREATED).entity(messageService.addMessage(message)).build();
		// created will set status 201 and location/URI for new message created
		//return Response.created(new URI(""+newMessage.getId())).entity(newMessage).build();
		String newMessageId= String.valueOf(newMessage.getId());
		URI uri=uriInfo.getAbsolutePathBuilder().path(newMessageId).build("");
		
		return Response.created(uri).entity(newMessage).build();
		
		//return messageService.addMessage(message);
	}
	
	
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id,Message message){
			message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void removeMessage(@PathParam("messageId") long id){
		System.out.println("in delete"+id);
		 messageService.removeMessage(id);
	}
	
	/**
	 * CommentResource
	 * 
	 * @return
	 */
	@Path("/{messageId}/comments")
	public CommentResource getComments(){
			return new CommentResource();
	}
}
