package com.kei.cisco.dao.impl;

import com.kei.cisco.dao.AbstractDao;
import com.kei.cisco.dao.TestDao;
import com.kei.cisco.model.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("testDao")
public class TestDaoImpl extends AbstractDao<Integer, Test> implements TestDao {

    public void save(Test test) {
        persist(test);
    }

    public void updateTest(Test test) {
        getSession().update(test);
    }

    public Test findById(int id) {
        return getByKey(id);
    }

    public List<Test> findAll(){
        return createEntityCriteria().list();
    }

    public void deleteById(int id) {
        delete(getByKey(id));
    }
}
