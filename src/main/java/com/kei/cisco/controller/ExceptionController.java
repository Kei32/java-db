package com.kei.cisco.controller;

import com.kei.cisco.service.AuthUserService;
import com.kei.cisco.service.UserProfileService;
import com.kei.cisco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExceptionController {

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserService userService;

	@Autowired
	AuthUserService authUserService;
	
	@RequestMapping("/404")
	public String pageNot(ModelMap model) {
		model.addAttribute("title", "404");
		return "404";
	}

	@RequestMapping("/403")
	public String accessDe(ModelMap model) {
		model.addAttribute("title", "403");
		return "403";
	}
}