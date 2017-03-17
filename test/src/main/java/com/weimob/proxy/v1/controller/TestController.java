package com.weimob.proxy.v1.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "v1/proxy")
@Slf4j
public class TestController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView sayHello(ModelMap modelMap) {

		log.info("hello");

		modelMap.put("username", "zhangjin");

		modelMap.put("password", "hello");

		return new ModelAndView("index", "username", "zhangjin");
	}
}
