package com.kei.cisco.dao.impl;

import com.kei.cisco.dao.AbstractDao;
import com.kei.cisco.dao.ClassDao;
import com.kei.cisco.model.Class;
import com.kei.cisco.model.Course;
import com.kei.cisco.model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("classDao")
public class ClassDaoImpl extends AbstractDao<Integer, Class> implements ClassDao {

    public void save(Class classe) {
        persist(classe);
    }

    public Class findById(int id) {
        return getByKey(id);
    }

    public List<Class> findAll(){
        return createEntityCriteria().list();
    }

    public void delete(int id) {
        delete(id);
    }
}
