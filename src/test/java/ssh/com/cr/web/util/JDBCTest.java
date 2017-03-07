package ssh.com.cr.web.util;

import org.junit.Test;

import com.cr.web.db.JDBC;

public class JDBCTest {

    @Test
    public void test() {
        try {
            JDBC.initUser(null, "3306",  null,  "654321");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void finallyTest() throws Exception {
    	try {
			throw new Exception();
		} catch(Exception e) {
			throw new Exception();
		} finally {
			// TODO: handle finally clause
			System.out.println("finally");
		}
    }
}
