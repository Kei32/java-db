package com.kei.cisco.service;


import com.kei.cisco.model.Class;
import com.kei.cisco.model.UserProfile;

import java.util.List;

public interface ClassService {

	void save(Class classe);

	Class findById(int id);

	List<Class> findAll();

	void delete(int id);
}