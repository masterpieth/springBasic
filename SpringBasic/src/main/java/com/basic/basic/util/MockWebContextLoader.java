package com.basic.basic.util;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.test.context.support.AbstractContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

public class MockWebContextLoader extends AbstractContextLoader {
	
	private static final ServletContext SERVLET_CONTEXT = new MockServletContext("/src/main/webapp", new FileSystemResourceLoader());
	private static final GenericWebApplicationContext WEB_CONTEXT = new GenericWebApplicationContext();
	
	protected BeanDefinitionReader initBeanDefinitionReader (final GenericApplicationContext context) {
		return new XmlBeanDefinitionReader(context);
	}

	@Override
	public ApplicationContext loadContext(String... locations) throws Exception {
		SERVLET_CONTEXT.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, WEB_CONTEXT);
		WEB_CONTEXT.setServletContext(SERVLET_CONTEXT);
		initBeanDefinitionReader(WEB_CONTEXT).loadBeanDefinitions(locations);
		AnnotationConfigUtils.registerAnnotationConfigProcessors(WEB_CONTEXT);
		WEB_CONTEXT.refresh();
		WEB_CONTEXT.registerShutdownHook();
		return WEB_CONTEXT;
	}

	@Override
	protected String getResourceSuffix() {
		return ".xml";
	}

	@Override
	public ApplicationContext loadContext(MergedContextConfiguration mergedConfig) throws Exception {
		return null;
	}

}
