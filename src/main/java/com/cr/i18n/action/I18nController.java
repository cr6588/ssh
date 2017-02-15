package com.cr.i18n.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cr.i18n.service.I18nSer;
import com.cr.web.annotation.PagerResolver;
import com.cr.web.bean.I18n;
import com.cr.web.bean.Language;
import com.cr.web.bean.PagerInfo;
import com.cr.web.bean.PagerStruct;
import com.cr.web.bean.RequestResult;
import com.cr.web.bean.User;
import com.cr.web.util.RequestSessionUtil;

@Controller
@RequestMapping("/i18n")
public class I18nController {
	Logger logger = Logger.getLogger(I18nController.class);

	@Autowired
	private I18nSer i18nSer;
	// @Autowired
	// private MessageUtil messageUtil;

    @RequestMapping(value = "/{pageName}", method = RequestMethod.GET)
    public ModelAndView viewAdminManagePages(HttpServletRequest request, @PathVariable("pageName") String pageName) throws Exception {
        String path = RequestSessionUtil.getDevicePath(request) + "/i18n/" + pageName;
        return new ModelAndView(path, RequestSessionUtil.getRequestParamData(request));
    }

	@RequestMapping(value = "/addOrUpdateI18n", method = RequestMethod.POST)
	@ResponseBody
	public RequestResult<String> addOrUpdateI18n(@RequestBody I18n I18n) {
		RequestResult<String> result = new RequestResult<String>();
		try {
			if (I18n.getId() == null || I18n.getId() == 0) {
				this.i18nSer.addI18n(I18n);
			} else {
				this.i18nSer.updateI18n(I18n);
			}
			// DataBaseMessageResource dbMessageSource =
			// (DataBaseMessageResource) messageUtil.getMessageSource();
			// dbMessageSource.loadI18NMessages();
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setCode(100);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/deleteI18n", method = RequestMethod.POST)
	@ResponseBody
	public RequestResult<String> deleteI18n(@RequestParam("no") Long no) {
		RequestResult<String> result = new RequestResult<String>();
		try {
			this.i18nSer.deleteI18n(no);
			result.setCode(0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setCode(100);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/getI18n", method = RequestMethod.POST)
	@ResponseBody
	public RequestResult<I18n> getI18n(HttpServletRequest request) {
		RequestResult<I18n> result = new RequestResult<I18n>();
		Map<String, Object> params = RequestSessionUtil.getRequestParamData(request);
		try {
			result.setBody(this.i18nSer.getI18n(params));
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setCode(100);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/getI18nList", method = RequestMethod.POST)
	@ResponseBody
	public PagerStruct<I18n> getI18nList(@RequestParam(value = "keyWord", required = false) String keyWord,
			@RequestParam(value = "language", required = false) String languageStr, @PagerResolver PagerInfo pagerParam) {
		PagerStruct<I18n> result = new PagerStruct<I18n>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyWord", keyWord);
		Language language = Language.create(languageStr);
		params.put("language", language);
		try {
			result.setRows(this.i18nSer.getI18nList(params, pagerParam));
			result.setTotal(this.i18nSer.getI18nListCnt(params));
		} catch (Exception e) {
		    logger.error(e);
			logger.error(e.getMessage());
		}
		return result;
	}

	   @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
	    @ResponseBody
	    public RequestResult<String> addOrUpdateUser(@RequestBody User User) {
	        RequestResult<String> result = new RequestResult<String>();
	        try {
	            if (User.getId() == null || User.getId() == 0) {
	                this.i18nSer.addUser(User);
	            } else {
	                this.i18nSer.updateUser(User);
	            }
	        } catch (Exception e) {
	            logger.error(e.getMessage());
	            result.setCode(100);
	            result.setMessage(e.getMessage());
	        }
	        return result;
	    }

	    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	    @ResponseBody
	    public RequestResult<String> deleteUser(@RequestParam("id") Long id) {
	        RequestResult<String> result = new RequestResult<String>();
	        try {
	            this.i18nSer.deleteUser(id);
	            result.setCode(0);
	        } catch (Exception e) {
	            logger.error(e.getMessage());
	            result.setCode(100);
	            result.setMessage(e.getMessage());
	        }
	        return result;
	    }

	    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
	    @ResponseBody
	    public RequestResult<User> getUser(HttpServletRequest request) {
	        RequestResult<User> result = new RequestResult<User>();
	        Map<String, Object> params = RequestSessionUtil.getRequestParamData(request);
	        try {
	            result.setBody(this.i18nSer.getUser(params));
	        } catch (Exception e) {
	            logger.error(e.getMessage());
	            result.setCode(100);
	            result.setMessage(e.getMessage());
	        }
	        return result;
	    }

	    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	    @ResponseBody
	    public PagerStruct<User> getUserList(@RequestParam(value = "keyWord", required = false) String keyWord,
	            @RequestParam(value = "language", required = false) String languageStr, @PagerResolver PagerInfo pagerParam) {
	        PagerStruct<User> result = new PagerStruct<User>();
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("keyWord", keyWord);
	        Language language = Language.create(languageStr);
	        params.put("language", language);
	        try {
	            result.setRows(this.i18nSer.getUserList(params, pagerParam));
	            result.setTotal(this.i18nSer.getUserListCnt(params));
	        } catch (Exception e) {
	            logger.error(e);
	            logger.error(e.getMessage());
	        }
	        return result;
	    }
}
