package com.kei.cisco.dao;

import com.kei.cisco.model.Class;
import com.kei.cisco.model.Course;

import java.util.List;

public interface CourseDao {

    void save(Course course);

    Course findById(int id);

    List<Course> findAll();

    void delete(int id);
}

