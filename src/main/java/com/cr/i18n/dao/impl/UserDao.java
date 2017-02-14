package com.cr.i18n.dao.impl;

import java.util.List;
import java.util.Map;

import com.cr.web.bean.PagerInfo;
import com.cr.web.bean.User;

public interface UserDao {

    List<User> getUserList(Map<String, Object> param, PagerInfo pager) throws Exception;

    User getUser(Map<String, Object> param) throws Exception;

    void addUser(User user) throws Exception;

    void updateUser(User User) throws Exception;

    void deleteUser(Long id) throws Exception;

    int getUserListCnt(Map<String, Object> param) throws Exception;

}
