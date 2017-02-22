package ssh.com.cr.i18n.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cr.i18n.dao.impl.UserDao;
import com.cr.web.bean.PagerInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-db.xml", "classpath:spring-aop.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    public long getTime(int page) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        PagerInfo pager = new PagerInfo();
        pager.setPage(page);
        pager.setSize(10);
        long start = System.currentTimeMillis();
        userDao.getUserList(map, pager);
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

}
