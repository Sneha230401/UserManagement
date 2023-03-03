package com.usermgmt.controller;

import com.usermgmt.model.User;
import com.usermgmt.repository.UserRepository;
import com.usermgmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
/**
 * @author PES1UG19CS490 Sneha P
 */
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register/home")
	public String register() {

		return "register";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "register";
	}

	@PostMapping("/process_register")
	public String processRegister(Model model, User user) {

		System.out.println(user.toString());

		boolean success = userService.registerUser(user);

		if (success) {
			return "register_success";
		} else {
			model.addAttribute("errorMessage", "Error: " + "Email already exists");
			return "register";
		}

	}

	@GetMapping("/process_register")
	public String processRegister(Model model, String firstName) {

		System.out.println(firstName);
		User user = new User();
		user.setFirstName(firstName);

		boolean success = userService.registerUser(user);

		if (success) {
			return "register_success";
		} else {
			model.addAttribute("errorMessage", "Error: " + "Email already exists");
			return "register";
		}

	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/process_login")
	public String processLogin(Model model, @ModelAttribute("email") String email) {

		User validUser = userRepo.findByEmail(email);

		if (validUser == null) {
			model.addAttribute("errorMessage", "Error: " + "invalid login");
			return "login";
		}

		return "login_success";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "index";
	}

}
