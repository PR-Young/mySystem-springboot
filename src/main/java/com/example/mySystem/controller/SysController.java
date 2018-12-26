package com.example.mySystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * sprongboot默认支持访问.html页面 <br>
 * 要访问.jsp页面需要在yml配置文件中配置
 * 
 * @author 1065569578@qq.com
 *
 */
@Controller
@RequestMapping("/sys")
public class SysController {

	private static Logger logger = LoggerFactory.getLogger(SysController.class);

	@RequestMapping("/demo")
	public String index() {
		logger.info("ok");
		return "demo";
	}

	@RequestMapping("/menulist")
	public String menu() {
		return "menulist";
	}

	/**
	 * 默认不支持jsp页面访问
	 * 
	 * @return
	 */
	@RequestMapping("/index1")
	public String index1() {
		return "index1.jsp";
	}

}
