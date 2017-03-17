package com.weimob.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

	@Bean
	public HandlerMapping resourceHandlerMapping() {
		return super.resourceHandlerMapping();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// registry.addResourceHandler("/html/**").addResourceLocations("/html/");
		// registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		// registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		// registry.addResourceHandler("/images/**").addResourceLocations(
		// "/images/");
		// registry.addResourceHandler("/jsp/**").addResourceLocations("/jsp/");
	}

	@Bean
	public ViewResolver viewResolver() {
		// InternalResourceViewResolver viewResolver = new
		// InternalResourceViewResolver();
		// viewResolver.setPrefix("/jsp/");
		// viewResolver.setSuffix(".jsp");
		// return viewResolver;

		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setPrefix("");
		viewResolver.setSuffix(".ftl");
		return viewResolver;
	}

	@Bean
	public FreeMarkerConfig FreeMarkerConfigurer() {

		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();

		freeMarkerConfigurer.setTemplateLoaderPath("/ftl/");

		freeMarkerConfigurer.setDefaultEncoding("utf-8");

		return freeMarkerConfigurer;
	}
}
