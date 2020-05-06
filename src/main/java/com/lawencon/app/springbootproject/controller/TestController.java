package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.lawencon.app.springbootproject.model.payload.response.MessageResponse;
import com.lawencon.app.springbootproject.service.TestService;

@RestController
@CrossOrigin("*")
@RequestMapping("/test")
public class TestController extends BaseController{

	@Autowired
	private TestService testService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
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
	@PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('STUDENT')")
	public ResponseEntity<?> getListId(@PathVariable("idTest") String idTest){
		try {
			Test test = testService.findById(idTest);
			return new ResponseEntity<>(test, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/Test/{idSubcourse}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('STUDENT')")
	public ResponseEntity<?> getTest(@PathVariable("idSubcourse") String idSubcourse){
		List<?> listTest = new ArrayList<>();
		try {
			listTest = testService.findTestBySubcourse(idSubcourse);
			return new ResponseEntity<>(listTest, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	@PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
	public ResponseEntity<?> getInsert(@RequestBody String content) throws Exception {
		Test test = readValue(content, Test.class);
		if (testService.cekTest(test) == true) {
			return ResponseEntity.badRequest().body(new MessageResponse("Test Has Been Set on This Subcourse !"));
		}
		testService.insert(test);
		return ResponseEntity.ok().body(new MessageResponse("Success Insert"));
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Test test = readValue(content, Test.class);
			testService.update(test);
			return new ResponseEntity<>(test, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idTest}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER')")
	public ResponseEntity<?> getDelete(@PathVariable("idTest") String idTest){
		try {
			testService.delete(idTest);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
