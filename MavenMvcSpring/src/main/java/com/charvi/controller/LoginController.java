package com.charvi.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.charvi.model.LoginForm;
import com.charvi.service.UserService;
import com.charvi.userInfo.User;

@Controller
@RequestMapping(value = { "/login", "/loginpage" })
// can handle multiple incoming request with the matching url
public class LoginController {

	@Autowired
	UserService userService;

	/*
	 * Binding of the Login.jsp page using the command name "loginform" to a
	 * model object which is here new LoginForm(). When a submit is hit data is
	 * bound to this form and will be send to the server. commandname tells
	 * which jsp page to bind with which model
	 * 
	 * Initially when index.jsp redirects to the login.jsp page a Get method is
	 * called.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String bindForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	/*
	 * When the user enters data and hits submit a post method is called.
	 */

	// Hibernate Validator is the reference implementation for JSR 303

	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@Valid LoginForm loginForm, BindingResult result,
			Model model) {

		try {
			
			// if user enters blank submit then this condition will hold true
			if (result.hasErrors()) {
				return "login";
			} 
			
			// fetches the validated result from the Login Service class
			boolean isValidPassword = userService.isValidPassword(loginForm.getUserName(), loginForm.getPassword());
			
			if(isValidPassword){
				// storing all the user list present in the database and then adding it to the model attribute
				List<User> listOfUsers = userService.getUsers();
				model.addAttribute("listOfUsers", listOfUsers);
				return "loginSuccess";
			}
			else {
				// return to login page if password does not match
				result.rejectValue("userName", "global.exception.message");
				return "login";
			}
		} catch (Exception e) {
			result.rejectValue("userName", "global.exception.message");
			return "login";
		}

	}
}
