package com.friendbook.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.friendbook.model.User;
import com.friendbook.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String username = generateUsername(user.getFullName());
		user.setUsername(username);
		return userRepository.save(user);
	}

	public String generateUsername(String fullName) {
	    String cleaned = fullName.replaceAll("\\s+", "");

	    if (cleaned.isEmpty()) {
	        cleaned = "User"; 
	    }
	    String namePart = cleaned.substring(0, Math.min(5, cleaned.length()));
	    namePart = Character.toUpperCase(namePart.charAt(0)) + namePart.substring(1).toLowerCase();
	    int numberPart = 100 + new Random().nextInt(900); // 3-digit number
	    return namePart + numberPart;
	}

	public Optional<User> getByEmail(String email){
		return userRepository.findByEmail(email);
	}
}
