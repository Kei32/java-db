package com.kei.cisco.service.impl;

import com.kei.cisco.dao.TestDao;
import com.kei.cisco.model.Test;
import com.kei.cisco.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao dao;

	
	public void save(Test test){
		dao.save(test);
	}

	public void updateTest(Test test){
		dao.updateTest(test);
	}
	
	public Test findById(int id) {
		return dao.findById(id);
	}

	public List<Test> findAll() {
		return dao.findAll();
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}
}
