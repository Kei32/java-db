package com.kei.cisco.service;

import com.kei.cisco.model.Test;

import java.util.List;

public interface TestService {

	void save(Test test);

	void updateTest(Test test);
	
	Test findById(int id);

	List<Test> findAll();

	void deleteById(int id);
}