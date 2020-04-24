package com.lawencon.app.springbootproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class AksesController {

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}
	
	@GetMapping("/student")
	@PreAuthorize("hasRole('STUDENT')")
	public String studentAccess() {
		return "Student Board.";
	}

	@GetMapping("/trainer")
	@PreAuthorize("hasRole('TRAINER')")
	public String trainerAccess() {
		return "Trainer Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
