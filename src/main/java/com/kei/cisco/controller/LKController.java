package com.kei.cisco.controller;

import com.kei.cisco.model.User;
import com.kei.cisco.model.UserProfile;
import com.kei.cisco.service.AuthUserService;
import com.kei.cisco.service.UserProfileService;
import com.kei.cisco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/lk")
public class LKController {

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserService userService;

	@Autowired
	AuthUserService authUserService;
	
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