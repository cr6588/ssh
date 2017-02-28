package com.cr.db.action;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cr.web.bean.RequestResult;
import com.cr.web.util.RequestSessionUtil;
import com.cr.web.util.OSUtil;

@Controller
@RequestMapping("/db")
public class DbController {
    private static final String LogKey = "installLog";

    Logger logger = Logger.getLogger(DbController.class);

    @RequestMapping(value = "/{pageName}", method = RequestMethod.GET)
    public ModelAndView viewAdminManagePages(HttpServletRequest request, @PathVariable("pageName") String pageName) throws Exception {
        String path = RequestSessionUtil.getDevicePath(request) + "/db/" + pageName;
        return new ModelAndView(path, RequestSessionUtil.getRequestParamData(request));
    }

    @RequestMapping(value = "/installDb", method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<String> installDb(HttpServletRequest request, @RequestParam String srcPath,
                                           @RequestParam String port, @RequestParam String serviceName,
                                           @RequestParam(required = false, value = "unZipPath") String unZipPath) {
        RequestResult<String> result = new RequestResult<String>();
        StringBuffer sb = new StringBuffer();
        try {
            HttpSession session = request.getSession();
            session.setAttribute(LogKey, sb);
            String iniPath = decompress(srcPath, unZipPath, sb) + File.separator;
            updInstallLog(sb, 0, "解压完成<br>");
            updInstallLog(sb, 0, "更改mysql配置文件<br>");
            updMysqlConfig(iniPath + "my-default.ini", port);
            updInstallLog(sb, 0, "更改mysql配置文件完成<br>");
            updInstallLog(sb, 0, "将mysql安装成服务<br>");
            if(OSUtil.isWindows()) {
                //must be run as admin mode
                String cmdStr = "cmd /c " + iniPath + "bin/mysqld.exe  install " + serviceName + " --defaults-file=\"" + iniPath + "my-default.ini\"";
                String cmdReStr = OSUtil.exeCmd(cmdStr);
                updInstallLog(sb, 0, cmdReStr + "<br>");
                if(!cmdReStr.contains("Service successfully installed.")) {
                    throw new Exception("安装服务失败<br>");
                }
                updInstallLog(sb, 0, "安装服务成功<br>");
                cmdStr = "net start " + serviceName;
                cmdReStr = OSUtil.exeCmd(cmdStr);
                updInstallLog(sb, 0, cmdReStr + "<br>");
                if(cmdReStr.contains("Exited with error code ")) {
                    throw new Exception("启动失败<br>");
                }
            }
        } catch (Exception e) {
            updInstallLog(sb, 0, e.getMessage());
            e.printStackTrace();
            logger.error(e.getMessage());
            result.setCode(100);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/getInstallLog", method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<String> getInstallLog(HttpServletRequest request) {
        RequestResult<String> result = new RequestResult<String>();
        try {
            HttpSession session = request.getSession();
            StringBuffer sb = (StringBuffer) session.getAttribute(LogKey);
            if(sb != null && sb.length() != 0) {
                String log = sb.toString();
                result.setBody(log);
                updInstallLog(sb, log.length(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            result.setCode(100);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    public synchronized static void updInstallLog(StringBuffer sb,Integer len, String appendStr) {
        if(len != 0) {
            sb.delete(0, len);
        } else {
            sb.append(appendStr);
        }
    }

    public static String decompress(String srcPath, String dest, StringBuffer sb) throws Exception {
        File file = new File(srcPath);
        if (!file.exists()) {
            throw new RuntimeException(srcPath + "所指文件不存在");
        }
        if(dest == null || dest.equals("")) {
            dest = file.getParent();
        }
        ZipFile zf = new ZipFile(file);
        Enumeration<ZipEntry> entries = zf.getEntries();
        ZipEntry entry = null;
        int line = 0;
        String lastFirstDir = null; //上次entry一级目录
        String decompressDir = null;//解压目录，若压缩文件含有一层根目录则返回该根目录所在路径，否则返回dest路径
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
            System.out.print(++line +"解压" + entry.getName() + "\r\n");
            updInstallLog(sb, 0, line + "解压" + entry.getName() + "<br>");
            if (entry.isDirectory()) {
                String dirPath = dest + File.separator + entry.getName();
                File dir = new File(dirPath);
                dir.mkdirs();
            } else {
                File f = new File(dest + File.separator + entry.getName());
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                f.createNewFile();
                // 将压缩文件内容写入到这个文件中
                InputStream is = zf.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(f);
                int count;
                byte[] buf = new byte[8192];
                while ((count = is.read(buf)) != -1) {
                    fos.write(buf, 0, count);
                }
                is.close();
                fos.close();
            }
        }

        if(decompressDir == null) {
            decompressDir = dest + lastFirstDir;
        }
        return decompressDir;
    }

    public String updMysqlConfig(String configPath, String port) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader iniRead = new BufferedReader(new FileReader(configPath));
        String str = null;
        while((str = iniRead.readLine()) != null) {
            if(str.startsWith("[mysqld]")) {
                sb.append("[client]\r\nport=" + port + "\r\ndefault-character-set=utf8\r\n"); //!!!!与下处utf-8设置不同
                sb.append(str + "\r\n");
                sb.append("port=" + port + "\r\ncharacter_set_server=utf8\r\n");
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
