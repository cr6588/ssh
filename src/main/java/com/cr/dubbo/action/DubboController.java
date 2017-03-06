package com.cr.dubbo.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cr.dubbo.service.mvc.DubboSerMVCImpl;
import com.cr.web.util.RequestSessionUtil;

@Controller
@RequestMapping("/dubbo")
public class DubboController {

//    @Reference(version="1.0.0")
//    @Autowired
//    DubboSer dubboSer;
    @Autowired
    DubboSerMVCImpl dubboSer;

    @RequestMapping(value = "/{pageName}", method = RequestMethod.GET)
    public ModelAndView viewAdminManagePages(HttpServletRequest request, @PathVariable("pageName") String pageName) throws Exception {
        String path = RequestSessionUtil.getDevicePath(request) + "/dubbo/" + pageName;
        System.out.println(dubboSer.add());
        return new ModelAndView(path, RequestSessionUtil.getRequestParamData(request));
    }

    @RequestMapping("/add")
    @ResponseBody
    public int add() {
        return dubboSer.add();
    }
}
