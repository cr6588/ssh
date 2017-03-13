package ssh.com.cr;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cr.dubbo.service.dubbo.DubboService;
import com.cr.i18n.service.I18nSer;

public class MainServiceTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:dubbo-client.xml"});
        context.start();
 
        I18nSer demoService = (I18nSer)context.getBean("i18nSer"); // 获取远程服务代理
        int hello;
        try {
            hello = demoService.getUserListCnt(null);
            System.out.println( "***********************************************************" + hello ); // 显示调用结果
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // 执行远程方法
 
    }

    @Test
    public void testDubboSer() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:dubbo-client.xml"});
        context.start();
 
        DubboService demoService = (DubboService)context.getBean("dubboServiceImpl"); // 获取远程服务代理
        int hello;
        try {
            hello = demoService.add();
            System.out.println( hello ); // 显示调用结果
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // 执行远程方法
 
    }
}
