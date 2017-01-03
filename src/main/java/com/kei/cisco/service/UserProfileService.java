package com.kei.cisco.service;

import java.util.List;

import com.kei.cisco.model.UserProfile;

public interface UserProfileService {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
