package com.friendbook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "full name is required")
	private String fullName;

	@Column(unique = true)
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;

	@Column(unique = true)
	private String username;
	private String profileImage;

	private String favBooks;
	private String favPlaces;
	private String favSongs;

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getFavBooks() {
		return favBooks;
	}

	public void setFavBooks(String favBooks) {
		this.favBooks = favBooks;
	}

	public String getFavPlaces() {
		return favPlaces;
	}

	public void setFavPlaces(String favPlaces) {
		this.favPlaces = favPlaces;
	}

	public String getFavSongs() {
		return favSongs;
	}

	public void setFavSongs(String favSongs) {
		this.favSongs = favSongs;
	}

	public User(Long id, @NotBlank(message = "full name is required") String fullName,
			@Email(message = "Invalid email format") @NotBlank(message = "Email is required") String email,
			@NotBlank(message = "Password is required") String password, String username, String profileImage,
			String favBooks, String favPlaces, String favSongs) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.username = username;
		this.profileImage = profileImage;
		this.favBooks = favBooks;
		this.favPlaces = favPlaces;
		this.favSongs = favSongs;
	}

}
