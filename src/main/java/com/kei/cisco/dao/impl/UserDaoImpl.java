package com.kei.cisco.dao.impl;

import com.kei.cisco.dao.AbstractDao;
import com.kei.cisco.dao.UserDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kei.cisco.model.User;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public void save(User user) {
		persist(user);
	}
	
	public User findById(int id) {
		return getByKey(id);
	}

	public User findByLogin(String login) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("login", login));
		return (User) crit.uniqueResult();
	}

	public List<User> findByClass(String classes) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("class", classes));
		return (List<User>) crit.list();
	}

	public List<User> findAll() {
		return createEntityCriteria().list();
	}

}
