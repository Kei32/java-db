package com.kei.cisco.dao;

import com.kei.cisco.model.Class;
import com.kei.cisco.model.Test;

import java.util.List;

public interface TestDao {

    void save(Test test);

    void updateTest(Test test);

    Test findById(int id);

    List<Test> findAll();

    void deleteById(int id);
}

