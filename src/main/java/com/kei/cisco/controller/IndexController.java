package com.kei.cisco.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.kei.cisco.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.kei.cisco.model.User;
import com.kei.cisco.model.UserProfile;
import com.kei.cisco.service.UserProfileService;
import com.kei.cisco.service.UserService;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class IndexController {

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserService userService;

	@Autowired
	AuthUserService authUserService;
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("title", "Hi, Welcome to home");
		return "welcome";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		return "admin";
	}

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		return "dba";
	}
	
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "newuser";
	}

	/*
	 * This method will be called on form submission, handling POST request It
	 * also validates the user input
	 */
	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String saveRegistration(@Valid User user,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			System.out.println("There are errors");
			return "newuser";
		}
		userService.save(user);
		
		System.out.println("First Name : "+user.getFirstName());
		System.out.println("Last Name : "+user.getLastName());
		System.out.println("SSO ID : "+user.getSsoId());
		System.out.println("Password : "+user.getPassword());
		System.out.println("Email : "+user.getEmail());
		System.out.println("Checking UsrProfiles....");
		if(user.getUserProfiles()!=null){
			for(UserProfile profile : user.getUserProfiles()){
				System.out.println("Profile : "+ profile.getType());
			}
		}
		
		model.addAttribute("success", "User " + user.getFirstName() + " has been registered successfully");
		return "registrationsuccess";
	}
	
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

}