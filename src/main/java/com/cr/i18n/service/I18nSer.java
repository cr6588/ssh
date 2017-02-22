package com.cr.i18n.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.i18n.dao.I18nDao;
import com.cr.i18n.dao.impl.UserDao;
import com.cr.web.bean.I18n;
import com.cr.web.bean.PagerInfo;
import com.cr.web.bean.User;
import com.cr.web.db.DynamicDataSourceHolder;


@Service
public class I18nSer {

    @Autowired
    private I18nDao i18nDao;
    @Autowired
    private UserDao userDao;

    public List<I18n> getI18nList(Map<String, Object> param, PagerInfo pager) throws Exception{
        return i18nDao.getI18nList(param, pager);
    }

    public I18n getI18n(Map<String, Object> param) throws Exception {
        return i18nDao.getI18n(param);
    }

    public void addI18n(I18n i18n) throws Exception {
        i18nDao.addI18n(i18n);
    }

    public void updateI18n(I18n i18n) throws Exception {
        i18nDao.updateI18n(i18n);
    }

    public void deleteI18n(Long no) throws Exception {
        i18nDao.deleteI18n(no);
    }

    public int getI18nListCnt(Map<String, Object> params) throws Exception {
        return i18nDao.getI18nListCnt(params);
    }

    public List<User> getUserList(Map<String, Object> param, PagerInfo pager) throws Exception{
        return userDao.getUserList(param, pager);
    }

    public User getUser(Map<String, Object> param) throws Exception {
        return userDao.getUser(param);
    }

    public void addUser(User user) throws Exception {
        userDao.addUser(user);
    }

    public void updateUser(User user) throws Exception {
        userDao.updateUser(user);
    }

    public void deleteUser(Long id) throws Exception {
        userDao.deleteUser(id);
    }

    public int getUserListCnt(Map<String, Object> params) throws Exception {
        return userDao.getUserListCnt(params);
    }
}
