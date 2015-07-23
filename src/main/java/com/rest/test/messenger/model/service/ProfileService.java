package com.rest.test.messenger.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.rest.test.messenger.model.Profile;
import com.rest.test.messenger.stub.MockDB;

public class ProfileService {
	
	private Map <String, Profile> profiles= MockDB.getProfiles();
	
	public ProfileService(){
		profiles.put("cooldude", new Profile(1L,"cooldude","firstName1","lastName1"));
		profiles.put("coolbuddy", new Profile(2L,"coolbuddy","firstName2","lastName2"));
	}
	
	
	public List<Profile>getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
}
