package com.cr.web.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.net.SMTPAppender;

public class JDBC {

    private static final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String driverName = MYSQL_JDBC_DRIVER;
    private String url;
    private String username;
    private String password;
    private Connection con = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private ResultSet rs = null;
    private String sql;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JDBC(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getCon() throws Exception {
        if (con == null) {
            this.loadDriver();
            if( password == null ){
                con = DriverManager.getConnection(url);
            } else {
                con = DriverManager.getConnection(url, username, password);
            }
            con.setAutoCommit(false);
        }
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void loadDriver() throws Exception {
        Class.forName(driverName);
    }

    public Statement getStmt() throws Exception {
        if (stmt == null) {
            stmt = this.getCon().createStatement();
        }
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public PreparedStatement getPstmt() throws Exception {
        if (pstmt == null) {
            pstmt = this.getCon().prepareStatement(sql);
        }
        return pstmt;
    }

    public void setPstmt(PreparedStatement pstmt) {
        this.pstmt = pstmt;
    }

    public CallableStatement getCstmt() throws Exception {
        if (cstmt == null) {
            cstmt = this.getCon().prepareCall(sql);
        }
        return cstmt;
    }

    public void setCstmt(CallableStatement cstmt) {
        this.cstmt = cstmt;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public void close() throws Exception {
        if (this.rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        if (cstmt != null) {
            cstmt.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public static void initRoot(String url, String port , String oldPassword, String newPassword) {
        if(url == null || url.equals("")) {
            url = "localhost";
        }
        url = "jdbc:mysql://" + url + ":" + (port == null || port.equals("") ? "3306" : port) + "/mysql?user=root&password=&allowMultiQueries=true&amp;useUnicode=true&amp;characterEncoding=UTF-8";
        JDBC jdbc = new JDBC(url, "root", oldPassword);
        String updPassword = "UPDATE user SET password=PASSWORD('" + newPassword + "') WHERE user='root'";
        try {
            jdbc.setSql(updPassword);
            jdbc.getPstmt().execute(updPassword);
            jdbc.setSql("FLUSH PRIVILEGES");
            jdbc.getPstmt().execute(jdbc.getSql());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        JDBC jdbc = new JDBC("jdbc:mysql://localhost:3306/test?allowMultiQueries=true&amp;useUnicode=true&amp;characterEncoding=UTF-8", "dev", "dev");
        String sql = "select * from i18n";
        jdbc.setSql(sql);
        try {
            ResultSet rs = jdbc.getStmt().executeQuery(sql);
            jdbc.setRs(rs);
            while (rs.next()) {
                String str = rs.getString(1);
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sql = "select * from i18n where no = ?";
        sql = "call selecti18n";
        jdbc.setSql(sql);
        try {
//            PreparedStatement pstmt = jdbc.getPstmt();
//            boolean b = pstmt.execute();
//            System.out.println(b);
////            pstmt.setInt(1, 1);
////            ResultSet rs = pstmt.executeQuery();
////            while (rs.next()) {
////                String str = rs.getString(1);
////                System.out.println(str);
////            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jdbc.setSql(sql);
        try {
            CallableStatement cstmt = jdbc.getCstmt();
            boolean b = cstmt.execute();
            System.out.println(b);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        jdbc.getCon().commit();
        try {
            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
