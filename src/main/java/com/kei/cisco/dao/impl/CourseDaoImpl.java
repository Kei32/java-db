package com.kei.cisco.dao.impl;

import com.kei.cisco.dao.AbstractDao;
import com.kei.cisco.dao.CourseDao;
import com.kei.cisco.model.Class;
import com.kei.cisco.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("courseDao")
public class CourseDaoImpl extends AbstractDao<Integer, Course> implements CourseDao {

    public void save(Course course) {
        persist(course);
    }

    public Course findById(int id) {
        return getByKey(id);
    }

    @SuppressWarnings("unchecked")
    public List<Course> findAll(){
        return createEntityCriteria().list();
    }

    public void delete(int id) {
        delete(id);
    }
}
