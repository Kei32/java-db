package com.kei.cisco.service.impl;

import com.kei.cisco.dao.ClassDao;
import com.kei.cisco.dao.CourseDao;
import com.kei.cisco.model.Class;
import com.kei.cisco.model.Course;
import com.kei.cisco.service.ClassService;
import com.kei.cisco.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao dao;

	
	public void save(Course course){
		dao.save(course);
	}
	
	public Course findById(int id) {
		return dao.findById(id);
	}

	public List<Course> findAll() {
		return dao.findAll();
	}

	public void delete(int id) {
		dao.delete(id);
	}
}
