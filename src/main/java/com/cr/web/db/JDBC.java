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

    /**
     * 初始化用户数据
     * 设置root密码为newPassword，删除任意@localhost用户，增加用户名密码为dev的用户，刷新权限
     * @param url mysql地址 eg:31.56.145.45 默认localhost
     * @param port 端口 默认3306
     * @param oldPassword 旧密码 默认null
     * @param newPassword 新密码
     * @throws Exception 
     */
    public static void initUser(String url, String port , String oldPassword, String newPassword) throws Exception {
        if(url == null || url.equals("")) {
            url = "localhost";
        }
        url = "jdbc:mysql://" + url + ":" + (port == null || port.equals("") ? "3306" : port) + "/mysql?user=root&password=&allowMultiQueries=true&amp;useUnicode=true&amp;characterEncoding=UTF-8";
        JDBC jdbc = new JDBC(url, "root", oldPassword);
        StringBuilder sql = new StringBuilder("UPDATE user SET password=PASSWORD('" + newPassword + "') WHERE user='root';");
        try {
            sql.append("DELETE FROM user Where User='' and Host='localhost';"); //删除 @localhost用户
            sql.append(getAddDevUserSql());
            sql.append("FLUSH PRIVILEGES");
            jdbc.setSql(sql.toString());
            jdbc.getPstmt().execute(jdbc.getSql());
        } catch (Exception e) {
            throw new Exception("初始化用户出错");
        } finally {
            try {
                jdbc.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static String getAddDevUserSql(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE USER 'dev'@'%' IDENTIFIED BY 'dev';");
        sb.append("GRANT GRANT OPTION ON *.* TO 'dev'@'%';");
        sb.append("GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, RELOAD, SHUTDOWN, PROCESS, FILE, REFERENCES, INDEX, ALTER, SHOW DATABASES, SUPER, CREATE TEMPORARY TABLES, LOCK TABLES, EXECUTE, REPLICATION SLAVE, REPLICATION CLIENT, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, CREATE USER, EVENT, TRIGGER ON *.* TO 'dev'@'%';");
        return sb.toString();
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
