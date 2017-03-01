package ssh.com.cr.web.util;

import org.junit.Test;

import com.cr.web.db.JDBC;

public class JDBCTest {

    @Test
    public void test() {
        JDBC.initRoot(null, "3308",  "6543212",  "6543212");
    }

}
