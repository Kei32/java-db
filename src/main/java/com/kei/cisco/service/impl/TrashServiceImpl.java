package com.kei.cisco.service.impl;

import com.kei.cisco.dao.TrashDao;
import com.kei.cisco.dao.UserDao;
import com.kei.cisco.model.Trash;
import com.kei.cisco.model.User;
import com.kei.cisco.service.TrashService;
import com.kei.cisco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("trashService")
@Transactional
public class TrashServiceImpl implements TrashService {

	@Autowired
	private TrashDao dao;

	@Override
	public int create(Trash trash) {
		return dao.create(trash);
	}

	@Override
	public Trash read(int id) {
		return dao.read(id);
	}

	@Override
	public List<Trash> read(String type) {
		return dao.read(type);
	}

	@Override
	public List<Trash> read() {
		return dao.read();
	}

	@Override
	public boolean update(Trash trash) {
		return dao.update(trash);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(id);
	}
}
