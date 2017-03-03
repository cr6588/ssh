package com.cr.i18n.service;

import java.util.List;
import java.util.Map;

import com.cr.web.bean.I18n;
import com.cr.web.bean.PagerInfo;
import com.cr.web.bean.User;

public interface I18nSer {

    List<I18n> getI18nList(Map<String, Object> param, PagerInfo pager) throws Exception;

    I18n getI18n(Map<String, Object> param) throws Exception;

    void addI18n(I18n i18n) throws Exception;

    void updateI18n(I18n i18n) throws Exception;

    void deleteI18n(Long no) throws Exception;

    int getI18nListCnt(Map<String, Object> params) throws Exception;

    List<User> getUserList(Map<String, Object> param, PagerInfo pager) throws Exception;

    User getUser(Map<String, Object> param) throws Exception;

    void addUser(User user) throws Exception;

    void updateUser(User user) throws Exception;

    void deleteUser(Long id) throws Exception;

    int getUserListCnt(Map<String, Object> params) throws Exception;

}
