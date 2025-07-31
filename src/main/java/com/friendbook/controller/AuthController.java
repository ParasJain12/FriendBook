package com.friendbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.friendbook.model.User;
import com.friendbook.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/signup")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String processSignUp(@ModelAttribute("user")@Valid User user, Model model, BindingResult result) {
		if(result.hasErrors()) {
			return "signup";
		}
		
		if(userService.getByEmail(user.getEmail()).isPresent()) {
			model.addAttribute("error", "Email already exists");
		}
		
		userService.registerUser(user);
		model.addAttribute("message", "User registered successfully");
		return "login";
	}
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
}
