package com.kei.cisco.controller;

import com.kei.cisco.helpers.AuthUserHelper;
import com.kei.cisco.service.UserProfileService;
import com.kei.cisco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/lk")
public class LKController {

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserService userService;

	@Autowired
	AuthUserHelper authUserHelper;
	
	@RequestMapping(value = {"/",""}, method = RequestMethod.GET)
	public String home(ModelMap model) {
		model.addAttribute("title", "Hi, Welcome to lk");
		return "welcome";
	}

	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String setting(ModelMap model) {
		model.addAttribute("title", "Hi, Welcome to lk settings");
		return "dba";
	}
	

}