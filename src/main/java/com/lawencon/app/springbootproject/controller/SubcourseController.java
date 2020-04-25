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

import com.lawencon.app.springbootproject.model.Subcourse;
import com.lawencon.app.springbootproject.service.SubcourseService;

@RestController
@CrossOrigin("*")
@RequestMapping("/subcourse")
public class SubcourseController extends BaseController{

	@Autowired
	private SubcourseService subcourseService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
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
	
	@GetMapping("/search/{idSubcourse}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListId(@PathVariable("idSubcourse") String idSubcourse){
		try {
			Subcourse subcourse = subcourseService.findById(idSubcourse);
			return new ResponseEntity<>(subcourse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/course/{idCourse}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListCourse(@PathVariable("idCourse") String idCourse){
		List<?> listCourse = new ArrayList<>();
		try {
			listCourse = subcourseService.findByCourse(idCourse);
			return new ResponseEntity<>(listCourse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listCourse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
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
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
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
	
	@DeleteMapping("/delete/{idSubcourse}")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getDelete(@PathVariable("idSubcourse") String idSubcourse){
		try {
			subcourseService.delete(idSubcourse);
			return new ResponseEntity<>("Success Delete", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Delete", HttpStatus.BAD_REQUEST);
		}
	}
}
