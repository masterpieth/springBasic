package com.basic.basic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.basic.basic.util.LocaleUtil;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/login/loginForm", method = RequestMethod.GET)
	public String loginForm() {
		return "/login/loginForm";
	}
	@RequestMapping(value = "/admin/adminHome", method = RequestMethod.GET)
	public String adminHome() {
		return "/admin/adminHome";
	}
	
	@RequestMapping(value = "/login/accessDenied", method = RequestMethod.GET)
	public String accessDenied() {
		return "/login/accessDenied";
	}
	
	@RequestMapping(value = "/intro/introduction", method = RequestMethod.GET)
	public String introduction() {
		return "/intro/introduction";
	}
	
	@RequestMapping(value = "/language", method = RequestMethod.GET)
	public String changeLang(HttpServletRequest request, HttpServletResponse response, String lang) {
		LocaleUtil.setLocale(request, response, lang);
		return "redirect:/";
	}
}
