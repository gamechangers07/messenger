package com.rest.test.messenger.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.rest.test.messenger.model.Comment;
import com.rest.test.messenger.model.ErrorMessage;
import com.rest.test.messenger.model.Message;
import com.rest.test.messenger.stub.MockDB;
import com.sun.jersey.api.NotFoundException;

public class CommentService {

	private Map<Long, Message> messages = MockDB.getMessages();

	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());

	}

	public Comment getComment(long commentId, Long messageId) {
		Message message= messages.get(messageId);
		ErrorMessage errorMessage= new ErrorMessage("Not Found",786,"http://www.google.com");
		Response response= Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		if(message==null){
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comments==null){
			throw new NotFoundException();
		}
		
		return comments.get(commentId);
	}

	public Comment addComment(Comment comment, Long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
		
	}

}
