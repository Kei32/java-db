package com.kei.cisco.dao;

import com.kei.cisco.model.Class;
import com.kei.cisco.model.Course;
import com.kei.cisco.model.Lesson;

import java.util.List;

public interface LessonDao {

    void save(Lesson lesson);

    Lesson findById(int id);

    List<Lesson> findAll();

    void delete(int id);
}

