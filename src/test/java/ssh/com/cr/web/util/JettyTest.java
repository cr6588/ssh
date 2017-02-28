package ssh.com.cr.web.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class JettyTest {

    @Test
    public void test() {
        Server server = new Server(8080);  
        
        WebAppContext context = new WebAppContext();  
        context.setContextPath("/myapp");  
        context.setWar("E:/share/test/struts2-blank.war");  
        server.setHandler(context);  
  
        server.start();  
        server.join();  
    }

}
