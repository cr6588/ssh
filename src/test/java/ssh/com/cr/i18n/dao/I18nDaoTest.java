package ssh.com.cr.i18n.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cr.i18n.dao.I18nDao;
import com.cr.i18n.dao.impl.UserDaoImpl;
import com.cr.web.bean.I18n;
import com.cr.web.bean.Language;
import com.cr.web.bean.PagerInfo;
import com.cr.web.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-db.xml", "classpath:spring-aop.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class I18nDaoTest {

    @Autowired
    I18nDao i18nDao;

    @Autowired
    UserDaoImpl userDao;

    // @Ignore
    @Test
    public void i18nTest() throws Exception {
        PagerInfo pager = new PagerInfo();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        int cnt = i18nDao.getI18nListCnt(paramMap);

        I18n in1 = new I18n();
        in1.setCode("test1");
        in1.setValue("test1_value");
        in1.setLanguage(Language.zh_CN);
        i18nDao.addI18n(in1);
        Assert.assertNotNull(in1.getId());
        paramMap.put("id", in1.getId());

        I18n get1 = i18nDao.getI18n(paramMap);
        Assert.assertNotNull(get1);
        Assert.assertEquals(in1.getId(), get1.getId());
        Assert.assertEquals(in1.getCode(), get1.getCode());
        Assert.assertEquals(in1.getValue(), get1.getValue());
        Assert.assertEquals(in1.getLanguage(), get1.getLanguage());
        Assert.assertEquals(in1.getActive(), get1.getActive());
        Assert.assertEquals(in1.getIsDelete(), get1.getIsDelete());

        Assert.assertNotNull(get1.getCreateDate());

        in1.setCode("test1_update");
        in1.setValue("test1_value_update");
        in1.setLanguage(Language.en_US);
        in1.setActive(false);
        in1.setIsDelete(true);
        i18nDao.updateI18n(in1);

        get1 = i18nDao.getI18n(paramMap);
        Assert.assertNotNull(get1);
        Assert.assertEquals(in1.getId(), get1.getId());
        Assert.assertEquals(in1.getCode(), get1.getCode());
        Assert.assertEquals(in1.getValue(), get1.getValue());
        Assert.assertEquals(in1.getLanguage(), get1.getLanguage());
        Assert.assertEquals(in1.getActive(), get1.getActive());

        // Assert.assertNotEquals(in1.getIsDelete(), get1.getIsDelete());

        I18n in2 = new I18n();
        in2.setCode("test2");
        in2.setValue("test2_value");
        in2.setLanguage(Language.zh_CN);
        i18nDao.addI18n(in2);

        paramMap.remove("id");

        List<I18n> list = i18nDao.getI18nList(paramMap, null);
        Assert.assertEquals(list.size(), cnt + 2);

        i18nDao.deleteI18n(in1.getId());

        Assert.assertEquals(cnt, i18nDao.getI18nListCnt(paramMap) - 1);

        pager.setPage(1);
        pager.setSize(10);

        i18nDao.getI18nList(paramMap, pager);

    }

    @Test
    @Ignore
    public void pluginSelectTestNoAddData() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        PagerInfo pager = new PagerInfo();
        pager.setPage(1);
        pager.setSize(10);
        i18nDao.getI18nList(map, pager);
        pager.setPage(2);
        i18nDao.getI18nList(map, pager);
        int i = 1;
    }

    @Test
    @Ignore
    public void pluginSelectTestWithAddData() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        PagerInfo pager = new PagerInfo();
        pager.setPage(1);
        pager.setSize(10);
        i18nDao.getI18nList(map, pager);
        pager.setPage(2);
        I18n in1 = new I18n();
        in1.setCode("test1");
        in1.setValue("test1_value");
        in1.setLanguage(Language.zh_CN);
        i18nDao.addI18n(in1);

        i18nDao.getI18nList(map, pager);
        int i = 1;
    }

    @Test
    public void userTest() {
        User user = userDao.findById(2222l);
        user = new User();
        user.setUsername("NMwHLaDu");
        Long time = System.currentTimeMillis();
        List<User> users = userDao.findByExample(user);
        System.out.println(System.currentTimeMillis() - time);
        for (User u : users) {
            System.out.println(u.getId() + " " + u.getUsername() + " " + u.getPassword());
        }
        users = userDao.findByExample(user);
    }
}
