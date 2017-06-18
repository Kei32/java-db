package com.kei.cisco.dao.impl;

import com.kei.cisco.dao.AbstractDao;
import com.kei.cisco.dao.TestDao;
import com.kei.cisco.dao.TrashDao;
import com.kei.cisco.model.Test;
import com.kei.cisco.model.Trash;
import com.kei.cisco.model.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("trashDao")
public class TrashDaoImpl implements TrashDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    private Criteria createEntityCriteria(){
        return getSession().createCriteria(Trash.class);
    }

    @Override
    public int create(Trash trash) {
        getSession().persist(trash);
        getSession().flush();
        return trash.getId();
    }

    @Override
    public Trash read(int id) {
        return (Trash) getSession().get(Trash.class, id);
    }

    @Override
    public List<Trash> read(String type) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("type", type));
        return (List<Trash>) criteria.list();
    }

    @Override
    public List<Trash> read() {
        return createEntityCriteria().list();
    }

    @Override
    public boolean update(Trash trash) {
        try{
            getSession().update(trash);
        }
        catch (HibernateException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try{
            Trash trash = (Trash) getSession().get(Trash.class, id);
            getSession().delete(trash);
        }
        catch (HibernateException e){
            return false;
        }
        return true;
    }
}
