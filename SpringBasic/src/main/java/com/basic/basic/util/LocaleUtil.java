package com.basic.basic.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

public class LocaleUtil {
	
	public static void setLocale(HttpServletRequest request, HttpServletResponse response, String lang) {
		Locale locale = StringUtils.parseLocaleString(lang);
		
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		localeResolver.setLocale(request, response, locale);
	}
}
