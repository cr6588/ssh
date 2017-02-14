package com.cr.i18n.dao.impl;
// Generated 2017-2-13 15:02:12 by Hibernate Tools 3.4.0.CR1


import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cr.web.bean.PagerInfo;
import com.cr.web.bean.User;

/**
 * Home object for domain model class User.
 * @author Hibernate Tools
 */
@Repository
public class UserDaoImpl implements UserDao {

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
    

    /* (non-Javadoc)
     * @see com.cr.i18n.dao.impl.UserDao#getUserList(java.util.Map, com.cr.web.bean.PagerInfo)
     */
    @Override
    public List<User> getUserList(Map<String, Object> param, PagerInfo pager) throws Exception {
        String hsql = "from user";
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        if (pager != null) {
            query.setMaxResults(pager.getSize());
            query.setFirstResult((pager.getPage() - 1) * pager.getSize());
        }
        List<User> i18s = query.list();
        return i18s;
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.dao.impl.UserDao#getUser(java.util.Map)
     */
    @Override
    public User getUser(Map<String, Object> param) throws Exception {
        String hsql = "";
        if (param.get("id") != null) {
            hsql = "from User User where User.id = ?";// +
                                                      // param.get("language");
        }
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        if (param.get("id") != null) {
            query.setParameter(0, Long.parseLong(param.get("id").toString()));
        }
        @SuppressWarnings("unchecked")
        List<User> Users = query.list();
        User User = null;
        if (Users != null) {
            User = Users.get(0);
        }
        return User;
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.dao.impl.UserDao#addUser(com.cr.web.bean.User)
     */
    @Override
    public void addUser(User user) throws Exception {
        sessionFactory.getCurrentSession().save(user);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.dao.impl.UserDao#updateUser(com.cr.web.bean.User)
     */
    @Override
    public void updateUser(User User) throws Exception {
        sessionFactory.getCurrentSession().update(User);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.dao.impl.UserDao#deleteUser(java.lang.Long)
     */
    @Override
    public void deleteUser(Long id) throws Exception {
        Query query = sessionFactory.getCurrentSession().createQuery("delete User where id = ?");
        query.setLong(0, id);
        query.executeUpdate();
        query.executeUpdate();  
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.dao.impl.UserDao#getUserListCnt(java.util.Map)
     */
    @Override
    public int getUserListCnt(Map<String, Object> param) throws Exception {
        String hsql = "select count(*) from user";
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        Integer count = Integer.parseInt(query.uniqueResult().toString());
        return count;
    }
}

