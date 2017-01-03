package com.kei.cisco.dao;

import java.util.List;

import com.kei.cisco.model.UserProfile;

public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
