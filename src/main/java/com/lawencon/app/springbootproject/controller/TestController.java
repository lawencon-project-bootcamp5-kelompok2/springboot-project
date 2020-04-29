package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.app.springbootproject.model.Test;
import com.lawencon.app.springbootproject.service.TestService;

@RestController
@CrossOrigin("*")
@RequestMapping("/test")
public class TestController extends BaseController{

	@Autowired
	private TestService testService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<?> listTest = new ArrayList<>();
		try {
			listTest = testService.findAll();
			return new ResponseEntity<>(listTest, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listTest, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/{idTest}")
	public ResponseEntity<?> getListId(@PathVariable("idTest") String idTest){
		try {
			Test test = testService.findById(idTest);
			return new ResponseEntity<>(test, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Test test = readValue(content, Test.class);
			testService.insert(test);
			return new ResponseEntity<>(test, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Insert", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Test test = readValue(content, Test.class);
			testService.update(test);
			return new ResponseEntity<>(test, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Update", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idTest}")
	public ResponseEntity<?> getDelete(@PathVariable("idTest") String idTest){
		try {
			testService.delete(idTest);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
