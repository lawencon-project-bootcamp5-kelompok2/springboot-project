package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.app.springbootproject.model.Login;
import com.lawencon.app.springbootproject.service.LoginService;


@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController extends BaseController{
	
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
	public ResponseEntity<?> getUpdate(@PathVariable("id") int id, @PathVariable("user") String user, @PathVariable("pass") String pass, @PathVariable("role") String role){
		try {
			loginService.update(id, user, pass, role);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
	
	@PostMapping("/deleteId/{id}")
	public ResponseEntity<?> getDeleteById(@PathVariable("id") int id){
		try {
			loginService.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to Deleted", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
}
