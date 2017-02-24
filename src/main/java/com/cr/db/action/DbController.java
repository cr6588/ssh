package com.cr.db.action;


import java.io.File;
import java.io.FileOutputStream;
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

@Controller
@RequestMapping("/db")
public class DbController {
    Logger logger = Logger.getLogger(DbController.class);
    
    @RequestMapping(value = "/{pageName}", method = RequestMethod.GET)
    public ModelAndView viewAdminManagePages(HttpServletRequest request, @PathVariable("pageName") String pageName) throws Exception {
        String path = RequestSessionUtil.getDevicePath(request) + "/db/" + pageName;
        return new ModelAndView(path, RequestSessionUtil.getRequestParamData(request));
    }

    @RequestMapping(value = "/installDb", method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<String> installDb(HttpServletRequest request, @RequestParam String path,
                                           @RequestParam String port, @RequestParam String serviceName) {
        RequestResult<String> result = new RequestResult<String>();
        try {
            decompress(path, "d:/", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode(100);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/installLog", method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<String> installLog(HttpServletRequest request) {
        RequestResult<String> result = new RequestResult<String>();
        try {
            HttpSession session = request.getSession();
            Object o = session.getAttribute("installLog");
            if(o != null) {
                StringBuffer sb = (StringBuffer) o;
                result.setBody(sb.toString());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode(100);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    public static void decompress(String srcPath, String dest, HttpServletRequest request) throws Exception {
        File file = new File(srcPath);
        if (!file.exists()) {
            throw new RuntimeException(srcPath + "所指文件不存在");
        }
        ZipFile zf = new ZipFile(file);
        Enumeration<ZipEntry> entries = zf.getEntries();
        ZipEntry entry = null;
        StringBuffer sb = new StringBuffer();
        request.getSession().setAttribute("installLog", sb);
        while (entries.hasMoreElements()) {
            entry = (ZipEntry) entries.nextElement();
            sb.append("解压" + entry.getName() + "\r\n");
            if (entry.isDirectory()) {
                String dirPath = dest + File.separator + entry.getName();
                File dir = new File(dirPath);
                dir.mkdirs();
            } else {
                // 表示文件
                File f = new File(dest + File.separator + entry.getName());
                if (!f.exists()) {
//                    String dirs = FileUtils.getParentPath(f);
                    String dirs = f.getParent();
                    File parentDir = new File(dirs);
                    parentDir.mkdirs();
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
    }
}
