package ssh.com.cr.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.junit.Test;

import com.cdzy.cr.util.FileUtil;
import com.cr.web.util.OSUtil;

public class CommandTest {

    OSUtil windows;
    @Test
    public void cmdTest() {
//        String cmdStr = "cmd /c D:/mysql-5.6.35-winx64/bin/mysqld.exe  install mysql-3309 --defaults-file=\"D:/mysql-5.6.35-winx64/my-default.ini\"";
//        String cmdStr = "net start mysql-3309";
        String cmdStr = "net stop mysql_3308";
      
        try {
            System.out.println(windows.exeCmd(cmdStr));
            cmdStr = "sc delete mysql_3308";
            System.out.println(windows.exeCmd(cmdStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isWindowsTest() {
        org.junit.Assert.assertTrue(windows.isWindows());
    }

    @Test
    public void cmdMysqlTest() {
        Process process;
        try {
            process = Runtime.getRuntime().exec("cmd /c  mysql -uroot -P 3308");
            
            PrintWriter out = new PrintWriter(process.getOutputStream());
            String cmd = "" ; //你的cmd命令
            InputStream is = process.getInputStream(); //cmd返回出来的信息
            byte[] buf = new byte[1024];  
            int size;  
            while(true) {  
                try {  
                    while((size = is.read(buf)) != -1) {  
                        //获取到第一次cmd返回的信息，然后根据返回后的信息再做第二次cmd写入
                        System.out.println(new String(buf,0,size,"GB2312")); 
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                break;  
            } 
            out.println(cmd); //输入你的命令
            out.flush(); //写到控制台
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        try {
            FileUtil.deleteFileDir("D:\\mysql-5.6.35-winx64");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
