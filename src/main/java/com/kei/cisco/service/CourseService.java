package com.kei.cisco.service;

import com.kei.cisco.model.Class;
import com.kei.cisco.model.Course;

import java.util.List;

public interface CourseService {

	void save(Course course);
	
	Course findById(int id);

	List<Course> findAll();

	void delete(int id);
}