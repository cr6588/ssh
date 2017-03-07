package ssh.com.cr.web.util;

import java.sql.ResultSet;

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

    @Test
    public void conUnknownDbTest() {
        JDBC jdbc = new JDBC("jdbc:mysql://118.123.8.140:3306?allowMultiQueries=true&amp;useUnicode=true&amp;characterEncoding=UTF-8", "root", "363505e8956fad91");
        String sql = "show databases";
        try {
            ResultSet rs = jdbc.getStmt().executeQuery(sql);
            jdbc.setRs(rs);
            while (rs.next()) {
                String str = rs.getString(1);
                System.out.println(str);
            }
            sql = "CREATE DATABASE `cy_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci";
            jdbc.getStmt().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
