package ssh.com.cr.db.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.junit.Test;

public class DbControllerTest {
    @Test
    public void test() {
        try {
            String str = depress("D:\\mysql-5.6.35-winx64.zip", "D:\\");
            System.out.println(str);
            str = depress("D:\\bootstrap-select.zip", "D:\\");
            System.out.println(str);
            str = updMysqlConfig("D:\\mysql-5.6.35-winx64\\my-default.ini");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String depress(String srcPath, String dest) throws IOException {
        File file = new File(srcPath);
        ZipFile zf = new ZipFile(file);
        String lastFirstDir = null;
        String decompressDir = null;
        Enumeration<ZipEntry> entries = zf.getEntries();
        ZipEntry entry = null;
        while (entries.hasMoreElements()) {
            entry = (ZipEntry) entries.nextElement();
            if(decompressDir == null) {
                String curfirstDir = null;
                if(entry.getName().contains("/")) { //File.separator windows:\\ entry.getName(): mysql-5.6.35-winx64/bin/echo.exe
                    String[] dirs = entry.getName().split("/");
                    curfirstDir = dirs[0];
                    if(lastFirstDir == null) {
                        lastFirstDir = curfirstDir;
                    }
                    if(!lastFirstDir.equals(curfirstDir)) {
                        decompressDir = dest;
                    }
                } else {
                    decompressDir = dest;
                }
                lastFirstDir = curfirstDir;
            }
        }
        zf.close();
        if(decompressDir == null) {
            decompressDir = dest + lastFirstDir;
        }
        return decompressDir;
    }

    public String updMysqlConfig(String configPath) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader iniRead = new BufferedReader(new FileReader(configPath));
        String str = null;
        while((str = iniRead.readLine()) != null) {
            if(str.startsWith("[mysqld]")) {
                sb.append("[client]\r\nport=3306\r\ndefault-character-set=utf8\r\n");
                sb.append(str + "\r\n");
                sb.append("port=3306\r\ncharacter_set_server=utf8\r\n");
            } else {
                sb.append(str + "\r\n");
            }
        }
        iniRead.close();
        FileWriter iniWrite = new FileWriter(configPath);
        iniWrite.write(sb.toString());
        iniWrite.close();
        return sb != null ? sb.toString() : null;
    }
}
