package com.basic.basic.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;


public class LocaleUtil {
	
	private static final Logger logger  = LoggerFactory.getLogger(LocaleUtil.class);
	
	public static void setLocale(HttpServletRequest request, HttpServletResponse response, String lang) {
		
		logger.debug("LocaleUtil setLocale 시작");
		
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		
		Locale locale;
		if(lang != null) locale = StringUtils.parseLocaleString(lang);
		else locale = localeResolver.resolveLocale(request);
		
		localeResolver.setLocale(request, response, locale);
		
		logger.debug("LocaleUtil setLocale 종료");
	}
}
