package com.kei.cisco.service.impl;

import com.kei.cisco.dao.ClassDao;
import com.kei.cisco.dao.UserDao;
import com.kei.cisco.model.Class;
import com.kei.cisco.model.User;
import com.kei.cisco.model.UserProfile;
import com.kei.cisco.service.ClassService;
import com.kei.cisco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("classService")
@Transactional
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassDao dao;

	
	public void save(Class classe){
		dao.save(classe);
	}
	
	public Class findById(int id) {
		return dao.findById(id);
	}

	public List<Class> findAll() {
		return dao.findAll();
	}

	public void delete(int id) {
		dao.delete(id);
	}
}
