package com.kei.cisco.service.impl;

import com.kei.cisco.dao.CourseDao;
import com.kei.cisco.dao.LessonDao;
import com.kei.cisco.model.Class;
import com.kei.cisco.model.Course;
import com.kei.cisco.model.Lesson;
import com.kei.cisco.service.CourseService;
import com.kei.cisco.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("lessonService")
@Transactional
public class LessonServiceImpl implements LessonService {

	@Autowired
	private LessonDao dao;

	
	public void save(Lesson lesson){
		dao.save(lesson);
	}
	
	public Lesson findById(int id) {
		return dao.findById(id);
	}

	public List<Lesson> findAll() {
		return dao.findAll();
	}

	public void delete(int id) {
		dao.delete(id);
	}
}
