package com.cr.i18n.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.cr.i18n.dao.I18nDao;
import com.cr.i18n.dao.impl.UserDao;
import com.cr.web.bean.I18n;
import com.cr.web.bean.PagerInfo;
import com.cr.web.bean.User;


//@Service
@Service //dubbo
//@Component
public class I18nSerImpl implements I18nSer {

    @Autowired
    private I18nDao i18nDao;
    @Autowired
    private UserDao userDao;

    /* (non-Javadoc)
     * @see com.cr.i18n.service.I18nSer#getI18nList(java.util.Map, com.cr.web.bean.PagerInfo)
     */
    @Override
    public List<I18n> getI18nList(Map<String, Object> param, PagerInfo pager) throws Exception{
        return i18nDao.getI18nList(param, pager);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.service.I18nSer#getI18n(java.util.Map)
     */
    @Override
    public I18n getI18n(Map<String, Object> param) throws Exception {
        return i18nDao.getI18n(param);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.service.I18nSer#addI18n(com.cr.web.bean.I18n)
     */
    @Override
    public void addI18n(I18n i18n) throws Exception {
        i18nDao.addI18n(i18n);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.service.I18nSer#updateI18n(com.cr.web.bean.I18n)
     */
    @Override
    public void updateI18n(I18n i18n) throws Exception {
        i18nDao.updateI18n(i18n);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.service.I18nSer#deleteI18n(java.lang.Long)
     */
    @Override
    public void deleteI18n(Long no) throws Exception {
        i18nDao.deleteI18n(no);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.service.I18nSer#getI18nListCnt(java.util.Map)
     */
    @Override
    public int getI18nListCnt(Map<String, Object> params) throws Exception {
        return i18nDao.getI18nListCnt(params);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.service.I18nSer#getUserList(java.util.Map, com.cr.web.bean.PagerInfo)
     */
    @Override
    public List<User> getUserList(Map<String, Object> param, PagerInfo pager) throws Exception{
        return userDao.getUserList(param, pager);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.service.I18nSer#getUser(java.util.Map)
     */
    @Override
    public User getUser(Map<String, Object> param) throws Exception {
        return userDao.getUser(param);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.service.I18nSer#addUser(com.cr.web.bean.User)
     */
    @Override
    public void addUser(User user) throws Exception {
        userDao.addUser(user);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.service.I18nSer#updateUser(com.cr.web.bean.User)
     */
    @Override
    public void updateUser(User user) throws Exception {
        userDao.updateUser(user);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.service.I18nSer#deleteUser(java.lang.Long)
     */
    @Override
    public void deleteUser(Long id) throws Exception {
        userDao.deleteUser(id);
    }

    /* (non-Javadoc)
     * @see com.cr.i18n.service.I18nSer#getUserListCnt(java.util.Map)
     */
    @Override
    public int getUserListCnt(Map<String, Object> params) throws Exception {
        return userDao.getUserListCnt(params);
    }
}
