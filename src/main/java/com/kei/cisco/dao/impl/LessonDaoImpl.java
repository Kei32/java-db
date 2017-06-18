package com.kei.cisco.dao.impl;

import com.kei.cisco.dao.AbstractDao;
import com.kei.cisco.dao.CourseDao;
import com.kei.cisco.dao.LessonDao;
import com.kei.cisco.model.Course;
import com.kei.cisco.model.Lesson;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("lessonDao")
public class LessonDaoImpl extends AbstractDao<Integer, Lesson> implements LessonDao {

    public void save(Lesson lesson) {
        persist(lesson);
    }

    public Lesson findById(int id) {
        return getByKey(id);
    }

    public List<Lesson> findAll(){
        return createEntityCriteria().list();
    }

    public void delete(int id) {
        delete(id);
    }
}
