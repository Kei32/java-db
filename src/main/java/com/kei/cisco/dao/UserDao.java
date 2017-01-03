package com.kei.cisco.dao;

import com.kei.cisco.model.User;

public interface UserDao {

	void save(User user);
	
	User findById(int id);
	
	User findBySSO(String sso);
	
}

