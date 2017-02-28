package ssh.com.cr.web.util;

import org.junit.Test;

import com.cr.web.util.OSUtil;

public class CommandTest {

    OSUtil windows;
    @Test
    public void cmdTest() {
//        String cmdStr = "cmd /c D:/mysql-5.6.35-winx64/bin/mysqld.exe  install mysql-3309 --defaults-file=\"D:/mysql-5.6.35-winx64/my-default.ini\"";
//        String cmdStr = "net start mysql-3309";
      String cmdStr = "sc delete mysql_3309";
        try {
            System.out.println(windows.exeCmd(cmdStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isWindowsTest() {
        org.junit.Assert.assertTrue(windows.isWindows());
    }
}
