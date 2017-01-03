package com.kei.cisco.service;

import com.kei.cisco.model.User;

public interface UserService {

	void save(User user);
	
	User findById(int id);
	
	User findBySso(String sso);
	
}