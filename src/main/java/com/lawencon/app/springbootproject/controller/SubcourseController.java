package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.app.springbootproject.model.Course;
import com.lawencon.app.springbootproject.model.Subcourse;
import com.lawencon.app.springbootproject.service.SubcourseService;

@RestController
@CrossOrigin("*")
@RequestMapping("/subcourse")
public class SubcourseController extends BaseController{

	@Autowired
	private SubcourseService subcourseService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<?> listCourse = new ArrayList<>();
		try {
			listCourse = subcourseService.findAll();
			return new ResponseEntity<>(listCourse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listCourse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getListId(@RequestBody String content){
		try {
			Subcourse subcourse = readValue(content, Subcourse.class);
			subcourseService.findById(subcourse);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/course")
	public ResponseEntity<?> getListCourse(@RequestBody String content){
		try {
			Course course = readValue(content, Course.class);
			subcourseService.findByCourse(course);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Subcourse subcourse = readValue(content, Subcourse.class);
			subcourseService.insert(subcourse);
			return new ResponseEntity<>("Success Insert", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Insert", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Subcourse subcourse = readValue(content, Subcourse.class);
			subcourseService.update(subcourse);
			return new ResponseEntity<>("Success Update", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Update", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> getDelete(@RequestBody String content){
		try {
			Subcourse subcourse = readValue(content, Subcourse.class);
			subcourseService.delete(subcourse);
			return new ResponseEntity<>("Success Delete", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Delete", HttpStatus.BAD_REQUEST);
		}
	}
}
