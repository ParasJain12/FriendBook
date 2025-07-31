package com.friendbook.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.friendbook.model.User;
import com.friendbook.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/profile")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String showProfilePage(Model model, Principal principal) {
		Optional<User> userOpt = userService.getByEmail(principal.getName());
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			model.addAttribute("user", user);
			model.addAttribute("followerCount", user.getFollowers().size());
			model.addAttribute("followingCount", user.getFollowing().size());
			// model.addAttribute("postCount", postRepository.countByUser(user));
		}
		return "profile";
	}

	@PostMapping("/update-favorites")
	public String updateFavorites(@RequestParam String favBooks, @RequestParam String favSongs,
			@RequestParam String favPlaces, Principal principal) {
		Optional<User> userOpt = userService.getByEmail(principal.getName());
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			user.setFavBooks(favBooks);
			user.setFavSongs(favSongs);
			user.setFavPlaces(favPlaces);
			userService.updateUser(user);
		}
		return "redirect:/profile";
	}

	@PostMapping("/update-image")
	public String updateProfileImage(@RequestParam("image") MultipartFile image, Principal principal,
			HttpServletRequest request) {
		Optional<User> userOpt = userService.getByEmail(principal.getName());
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (!image.isEmpty()) {
				try {
					String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
					Path uploadPath = Paths.get("src/main/resources/static/uploads");

					if (!Files.exists(uploadPath)) {
						Files.createDirectories(uploadPath);
					}

					Files.copy(image.getInputStream(), uploadPath.resolve(filename),
							StandardCopyOption.REPLACE_EXISTING);

					user.setProfileImage(filename);
					userService.updateUser(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/profile";
	}
}
