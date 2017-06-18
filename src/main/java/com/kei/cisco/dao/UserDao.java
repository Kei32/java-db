package com.kei.cisco.dao;

import com.kei.cisco.model.User;

import java.util.List;

public interface UserDao {

	void save(User user);
	
	User findById(int id);
	
	User findByLogin(String login);

	List<User> findByClass(String classes);

	List<User> findAll();
	
}

