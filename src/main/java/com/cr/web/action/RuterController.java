package com.cr.web.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cr.web.bean.I18n;
import com.cr.web.util.RequestSessionUtil;


@Controller
public class RuterController {
    /**
     * 页面跳转，将参数传递到页面解析
     * @param request
     * @param pageName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{pageName}", method = RequestMethod.GET)
    public ModelAndView viewAdminManagePages(HttpServletRequest request, @PathVariable("pageName") String pageName) throws Exception {
        return new ModelAndView(pageName, RequestSessionUtil.getRequestParamData(request));
    }

    @RequestMapping(value = "requestBody", method = RequestMethod.POST)
    @ResponseBody
    public void requestBody(@RequestBody I18n i18n) {
        System.out.println("requestBody test" + i18n.toString());
        
    }
}
