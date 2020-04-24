package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.app.springbootproject.model.ERole;
import com.lawencon.app.springbootproject.model.Login;
import com.lawencon.app.springbootproject.model.Role;
import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.model.Trainer;
import com.lawencon.app.springbootproject.payload.request.LoginRequest;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;
import com.lawencon.app.springbootproject.payload.response.JwtResponse;
import com.lawencon.app.springbootproject.payload.response.MessageResponse;
import com.lawencon.app.springbootproject.repository.RoleRepository;
import com.lawencon.app.springbootproject.security.jwt.JwtUtils;
import com.lawencon.app.springbootproject.service.LoginService;
import com.lawencon.app.springbootproject.service.StudentService;
import com.lawencon.app.springbootproject.service.TrainerService;
import com.lawencon.app.springbootproject.service.impl.UserDetailsImpl;


@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	StudentService studentService;
	
	@Autowired
	TrainerService trainerService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/show")
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
	public ResponseEntity<?> getListUsername(@PathVariable("user") String user){
		List<?> listUser = new ArrayList<>();
		try {
			listUser = loginService.findUsername(user);
		} catch (Exception e) {
			return new ResponseEntity<>(listUser, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listUser, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Login login = readValue(content, Login.class);
			loginService.insertUser(login);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to Insert", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@PostMapping("/update/{id}/{user}/{pass}/{role}")
	public ResponseEntity<?> getUpdate(@PathVariable("id") String id, @PathVariable("user") String user, @PathVariable("pass") String pass, @PathVariable("role") String role){
		try {
			loginService.update(id, user, pass, role);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@PostMapping("/deleteId/{id}")
	public ResponseEntity<?> getDeleteById(@PathVariable("id") String id){
		try {
			loginService.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to Deleted", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws Exception {
		if (loginService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Login user = new Login(signUpRequest.getNama(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
			
			Student s = new Student();
			s.setEmail(user.getEmail());
			s.setNamaStudent(user.getNama());
			s.setPassword(user.getPassword());
			s.setRole(userRole.getName().toString());
			studentService.createStudent(s);
			
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				case "trainer":
					Role modRole = roleRepository.findByName(ERole.ROLE_TRAINER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
					
					Trainer t = new Trainer();
					t.setEmail(user.getEmail());
					t.setNamaTrainer(user.getNama());
					t.setPassword(user.getPassword());
					t.setRole(modRole.getName().toString());
					try {
						trainerService.createTrainer(t);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		loginService.insertUser(user);

		return ResponseEntity.ok(new MessageResponse("Registered successfully!"));
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

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
}
