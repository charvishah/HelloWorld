package com.charvi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.charvi.model.LoginForm;
import com.charvi.model.RegisterForm;
import com.charvi.service.RegistrationService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@RequestMapping(method = RequestMethod.GET)
	public String bindForm(Model model) {
		model.addAttribute("registerForm", new RegisterForm());
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@Valid RegisterForm registerForm,
			BindingResult result, Model model) {

		System.out.println(result.hasErrors());
		// if user enters blank submit then this condition will hold true
		try {
			if (result.hasErrors()) {
				return "register";
			}
			
			// checks whether the user is already registered
			boolean doesUserExist = registrationService.doesUserExist(registerForm.getUserName());

			//System.out.println(doesUserExist);
			if (doesUserExist) {
				result.rejectValue("userName", "login.failed");
				return "register";
			} else {
				// register a  new user into the database
				registrationService.registerUser(registerForm.getUserName(), registerForm.getPassword());
				model.addAttribute("loginForm", new LoginForm());
				return "login";
			}

		} catch (Exception e) {
			result.rejectValue("userName", "global.exception.message");
			return "register";
		}
	}
}
