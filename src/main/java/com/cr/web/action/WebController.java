package com.cr.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cr.web.bean.Language;
import com.cr.web.bean.RequestResult;
import com.cr.web.message.DataBaseMessageResource;
import com.cr.web.message.MessageUtil;


@Controller
@RequestMapping("/web")
public class WebController {    

    public static Logger logger = Logger.getLogger(WebController.class);

    @RequestMapping(value = "/getMessages", method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<Map<String, String>> getMessages(HttpServletRequest request, @RequestParam(value = "locale", required = false) String lanaugeStr) {
        RequestResult<Map<String, String>> result = new RequestResult<Map<String, String>>();
        try {
            Language lanauge = Language.zh_CN;
            if (lanaugeStr != null) {
                lanauge = Language.create(lanaugeStr);
            }
            if (MessageUtil.getMessageSource() instanceof DataBaseMessageResource) {
                DataBaseMessageResource messageSource = (DataBaseMessageResource) MessageUtil.getMessageSource();
                Map<String, String> map = messageSource.getLocalMessagePool(lanauge);
                result.setBody(map);
            } else {
                throw new Exception("can not cast messageSource ");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode(100);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
