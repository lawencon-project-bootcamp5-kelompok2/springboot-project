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

import com.lawencon.app.springbootproject.model.Course;
import com.lawencon.app.springbootproject.service.CourseService;

@RestController
@CrossOrigin("*")
@RequestMapping("/course")
public class CourseController extends BaseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<?> listCourse = new ArrayList<>();
		try {
			listCourse = courseService.findAll();
			return new ResponseEntity<>(listCourse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listCourse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/{idCourse}")
	public ResponseEntity<?> getId(@PathVariable("idCourse") String idCourse){
		try {
			courseService.findById(idCourse);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Course course = readValue(content, Course.class);
			return new ResponseEntity<>(courseService.insert(course), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Insert", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Course course = readValue(content, Course.class);
			courseService.update(course);
			return new ResponseEntity<>("Success Update", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Update", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idCourse}")
	public ResponseEntity<?> getDelete(@PathVariable("idCourse") String idCourse){
		try {
			courseService.delete(idCourse);
			return new ResponseEntity<>("Success Delete", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Delete", HttpStatus.BAD_REQUEST);
		}
	}
}
