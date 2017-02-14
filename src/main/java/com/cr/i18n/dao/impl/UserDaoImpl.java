package com.cr.i18n.dao.impl;
// Generated 2017-2-13 15:02:12 by Hibernate Tools 3.4.0.CR1


import java.util.List;

import javax.naming.InitialContext;

import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cr.web.bean.User;

/**
 * Home object for domain model class User.
 * @author Hibernate Tools
 */
@Repository
public class UserDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;
    
    public void delete(User persistentInstance) {
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
        }
        catch (RuntimeException re) {
            throw re;
        }
    }
    
    public User merge(User detachedInstance) {
        try {
            User result = (User) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            return result;
        }
        catch (RuntimeException re) {
            throw re;
        }
    }
    
    public User findById(Long id) {
        try {
            User instance = (User) sessionFactory.getCurrentSession()
                    .get("user", id);
            if (instance==null) {
            }
            else {
            }
            return instance;
        }
        catch (RuntimeException re) {
            throw re;
        }
    }
    
    public List findByExample(User instance) {
        try {
            List results = sessionFactory.getCurrentSession()
                    .createCriteria("user")
                    .add(Example.create(instance))
            .list();
            return results;
        }
        catch (RuntimeException re) {
            throw re;
        }
    } 
    
    
}

