package com.kei.cisco.dao;

import com.kei.cisco.model.Class;
import com.kei.cisco.model.UserProfile;

import java.util.List;

public interface ClassDao {

    void save(Class classe);

    Class findById(int id);

    List<Class> findAll();

    void delete(int id);
}

