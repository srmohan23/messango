package com.chat.messango.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chat.messango.model.Users;
import com.chat.messango.services.EmailService;
import com.chat.messango.services.UserService;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		Users user = new Users();
		user.setGender(true);
		model.addObject("user", user);
		model.setViewName("User/index");
		return model;
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public ModelAndView createUser(@Valid Users user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		Users userExists = userService.findUserByEmail(user.getEmail());

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "This email already exists!");
		}
		if (bindingResult.hasErrors()) {
			model.setViewName("User/index");
		} else {
			userService.save(user);
			model.addObject("msg", "User has been registered successfully!");
			model.addObject("user", new Users());
			model.setViewName("User/index");
		}

		return model;
	}

	@RequestMapping(value = { "/chat" }, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findUserByEmail(auth.getName());
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		model.setViewName("User/home");
		return model;
	}

	@RequestMapping(value = { "/forgot" }, method = RequestMethod.GET)
	public ModelAndView forgot() {
		ModelAndView model = new ModelAndView();
		Users user = new Users();
		model.addObject("user", user);
		model.setViewName("User/forgot");
		return model;
	}

	@RequestMapping(value = { "/forgot" }, method = RequestMethod.POST)
	public ModelAndView forgot(@Valid Users user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		Users userExists = userService.findUserByEmail(user.getEmail());
		if (userExists == null) {
			bindingResult.rejectValue("email", "error.user", "This email already exists!");
		}
		if (!bindingResult.hasErrors()) {
			emailService.sendEmail(userExists);
			model.addObject("msg", "Mail sent successfully to your Email Id:" + user.getEmail());
			model.addObject("user", user);
			model.setViewName("User/forgot");
		}
		return model;
	}
}
