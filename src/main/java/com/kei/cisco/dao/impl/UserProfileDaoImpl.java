package com.kei.cisco.dao.impl;

import java.util.List;

import com.kei.cisco.dao.AbstractDao;
import com.kei.cisco.dao.UserProfileDao;
import com.kei.cisco.model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao {

	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll(){
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("type"));
		return (List<UserProfile>)crit.list();
	}
	
	public UserProfile findById(int id) {
		return getByKey(id);
	}
	
	public UserProfile findByType(String type) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("type", type));
		return (UserProfile) crit.uniqueResult();
	}
}
