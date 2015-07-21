package com.charvi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.charvi.model.LoginForm;
import com.charvi.service.UserService;
import com.charvi.userInfo.User;

@Controller
public class DeleteUserController {

	@Autowired
	UserService userService;

	@RequestMapping("/delete")
	public String processForm(HttpServletRequest req, HttpServletResponse res,
			Model model) {

		try {

			System.out.println("id = " + req.getParameter("id"));
			System.out.println("integer parse int: "
					+ Integer.parseInt(req.getParameter("id")));

			// pass on the user id when the user clicks a Delete button to
			// delete another user from
			// the database
			userService.deleteUser((Integer.parseInt(req.getParameter("id"))));
		} catch (Exception e) {
			// result.rejectValue("userName", "global.exception.message");
			// return "login";
		}

		// retrieve a new list of users from the database and add it to the model attribute
		List<User> listOfUsers = userService.getUsers();
		model.addAttribute("listOfUsers", listOfUsers);
		LoginForm lf = new LoginForm();
		// set the current user name to the loginform again 
		lf.setUserName(req.getParameter("currentUser"));
		model.addAttribute("loginForm", lf);
		return "loginSuccess";
	}

}
