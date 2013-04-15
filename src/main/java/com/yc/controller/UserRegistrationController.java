package com.yc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yc.helper.PasswordEncoder;
import com.yc.model.Role;
import com.yc.model.User;
import com.yc.service.UserService;

@Controller
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/registration")
	public ModelAndView registrationPage() {
		ModelAndView modelAndView = new ModelAndView("user-registration/registration-form");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}
	
	@RequestMapping(value="/user/register")
	public ModelAndView registerUser(@ModelAttribute User user) {
		ModelAndView modelAndView = new ModelAndView("user-registration/registration-form");
		
		User tempUser = userService.getUser(user.getEmail());
		
		if (tempUser == null) {
						
			userService.addUser(user);
			
			modelAndView.addObject("user", user);
			modelAndView.addObject("success-msg", "message.user.success.register");
		} else {
			modelAndView.addObject("error-msg", "message.user.invalid.register");
		}
		return modelAndView;
	}

}