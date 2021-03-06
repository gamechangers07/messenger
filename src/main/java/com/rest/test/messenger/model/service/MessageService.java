package com.rest.test.messenger.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.rest.test.messenger.model.Message;
import com.rest.test.messenger.stub.MockDB;

public class MessageService {
	
	private Map <Long, Message> messages= MockDB.getMessages();
	public List<Message>getAllMessages(){
//		Message m1= new Message(1L,"Hello World!","Mike");
//		Message m2= new Message(1L,"Hello Jersery!","Mike");
//		ArrayList<Message> list=	new ArrayList<Message>();
//		list.add(m1);
//		list.add(m2);
		//return list;
		return new ArrayList<Message>(messages.values());
	}
	
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messageForYear = new ArrayList<Message>();
		Calendar cal =Calendar.getInstance();
		for( Message message:messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year){
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start + size > list.size()) {
			return new ArrayList<Message>();
		}
		return list.subList(start, start + size);
	}
	
	
	public MessageService(){
		
		messages.put(1L, new Message(1L,"Hello World!","Mike"));
		messages.put(2L, new Message(2L,"Hello Jersery!","Mike2"));
	}
	
	public Message getMessage(long id){
		return messages.get(id);
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId()<=0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
}
