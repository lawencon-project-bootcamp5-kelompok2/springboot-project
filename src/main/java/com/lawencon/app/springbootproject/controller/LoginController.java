package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.app.springbootproject.model.Login;
import com.lawencon.app.springbootproject.model.payload.request.LoginRequest;
import com.lawencon.app.springbootproject.model.payload.request.SignupRequest;
import com.lawencon.app.springbootproject.model.payload.response.JwtResponse;
import com.lawencon.app.springbootproject.model.payload.response.MessageResponse;
import com.lawencon.app.springbootproject.security.jwt.JwtUtils;
import com.lawencon.app.springbootproject.service.LoginService;
import com.lawencon.app.springbootproject.service.impl.UserDetailsImpl;


@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/show")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Login>> getList(){
		List<Login> listUser = new ArrayList<>();
		try {
			listUser = loginService.findAll();
		} catch (Exception e) {
			return new ResponseEntity<List<Login>>(listUser, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Login>>(listUser, HttpStatus.OK);
	}
	
	@GetMapping("/show/username/{user}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getListUsername(@PathVariable("user") String user){
		List<?> listUser = new ArrayList<>();
		try {
			listUser = loginService.findUsername(user);
		} catch (Exception e) {
			return new ResponseEntity<>(listUser, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listUser, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Login login = readValue(content, Login.class);
			loginService.update(login);
			return new ResponseEntity<>(login, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/deleteId/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getDeleteById(@PathVariable("id") String id){
		try {
			loginService.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("deleted...",HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		try {
			loginService.signUp(signUpRequest);
			return ResponseEntity.ok(new MessageResponse("Registered successfully!"));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}
}
