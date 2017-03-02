package ssh.com.cr.web.util;

import org.junit.Test;

import com.cr.web.db.JDBC;

public class JDBCTest {

    @Test
    public void test() {
        try {
            JDBC.initUser(null, "3308",  null,  "654321");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
