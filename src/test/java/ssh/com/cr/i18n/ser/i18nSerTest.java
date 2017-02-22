package ssh.com.cr.i18n.ser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cr.i18n.service.I18nSer;
import com.cr.web.bean.PagerInfo;
import com.cr.web.db.DynamicDataSource;
import com.cr.web.db.DynamicDataSourceHolder;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-db.xml", "classpath:spring-aop.xml", "classpath:dispatcher.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class i18nSerTest {

    @Autowired
    I18nSer i18nSer;
    @Autowired
    private AnnotationSessionFactoryBean sessionFactory;

    public long getTime(int page) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        PagerInfo pager = new PagerInfo();
        pager.setPage(page);
        pager.setSize(10);
        long start = System.currentTimeMillis();
        i18nSer.getUserList(map, pager);
        long end = System.currentTimeMillis();
        return end - start;
    }
//   BigDecimal枚举常量用法摘要 
//   CEILING 
//   向正无限大方向舍入的舍入模式
//   DOWN
//   向零方向舍入的舍入模式
//   DOWN        
//   向零方向舍入的舍入模式
//   FLOOR
//   向负无限大方向舍入的舍入模式
//   HALF_DOWN
//   向最接近数字方向舍入的舍入模式，如果与两个相邻数字的距离相等，则向下舍入
//   HALF_EVEN 
//   向最接近数字方向舍入的舍入模式，如果与两个相邻数字的距离相等，则向相邻的偶数舍入
//   HALF_UP
//   向最接近数字方向舍入的舍入模式，如果与两个相邻数字的距离相等，则向上舍入
//   UNNECESSARY 
//   用于断言请求的操作具有精确结果的舍入模式，因此不需要舍入
//   UP 
//   远离零方向舍入的舍入模式
    //以tomcat7:run的maven命令运行能够正常调用读库，但是使用此处的单元测试虽然也会进入相应的aop接口但却不会使用读库 
    @Test
    public void selectTest() throws Exception {
        getTime(1); //第一次时间跨度大不取
        long totalTime = getTime(1) + getTime(1) + getTime(1);
        BigDecimal firstPageTime = new BigDecimal(totalTime).divide(new BigDecimal(3), 2 , RoundingMode.HALF_UP);
        System.out.println("三次查询总耗时：" + totalTime + ",平均值：" + firstPageTime.toString());
        int mediumPage = 25000;
        totalTime = getTime(mediumPage) + getTime(mediumPage) + getTime(mediumPage);
        BigDecimal mediumPageTime = new BigDecimal(totalTime).divide(new BigDecimal(3), 2 , RoundingMode.HALF_UP);
        System.out.println("三次查询总耗时：" + totalTime + ",平均值：" + mediumPageTime.toString());
        int lastPage = mediumPage * 2;
        totalTime = getTime(lastPage) + getTime(lastPage) + getTime(lastPage);
        BigDecimal lastPageTime = new BigDecimal(totalTime).divide(new BigDecimal(3), 2 , RoundingMode.HALF_UP);
        System.out.println("三次查询总耗时：" + totalTime + ",平均值：" + lastPageTime.toString());
    }

    @Test
    public void selectDy() {
        String hsql = "select count(*) from User";
//        Query query = sessionFactory.getObject().getCurrentSession().createQuery(hsql);
        AnnotationSessionFactoryBean asfb = sessionFactory;
        DynamicDataSource dds = (DynamicDataSource)asfb.getDataSource();
        Object dataSourceKey = dds.determineCurrentLookupKey();
        System.out.println("#################################" + dataSourceKey);
        DynamicDataSourceHolder.markSlave();
        dataSourceKey = dds.determineCurrentLookupKey();
        System.out.println("#################################" + dataSourceKey);
        Query query = sessionFactory.getObject().getCurrentSession().createQuery(hsql);
        Integer count = Integer.parseInt(query.uniqueResult().toString());
        System.out.println("#################################" + count);
    }
}
