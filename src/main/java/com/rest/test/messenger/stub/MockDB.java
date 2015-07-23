package com.rest.test.messenger.stub;

import java.util.HashMap;
import java.util.Map;

import com.rest.test.messenger.model.Message;
import com.rest.test.messenger.model.Profile;

public class MockDB {
	
	private static Map<Long, Message> messages = new HashMap<Long, Message>();
	private static Map<String, Profile> profiles = new HashMap<String, Profile>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	

}
