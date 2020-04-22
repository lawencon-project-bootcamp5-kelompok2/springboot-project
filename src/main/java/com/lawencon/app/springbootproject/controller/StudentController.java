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

import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.service.StudentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController extends BaseController{

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<?> listStudent = new ArrayList<>();
		try {
			listStudent = studentService.findAll();
			return new ResponseEntity<>(listStudent, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listStudent, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/{idStudent}")
	public ResponseEntity<?> getListId(@PathVariable("idStudent") String idStudent){
		try {
			studentService.findById(idStudent);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Student student = readValue(content, Student.class);
			return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Insert", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Student student = readValue(content, Student.class);
			studentService.updateStudent(student);
			return new ResponseEntity<>("Success Update", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Update", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idStudent}")
	public ResponseEntity<?> getDelete(@PathVariable("idStudent") String idStudent){
		try {
			studentService.deleteStudent(idStudent);
			return new ResponseEntity<>("Success Delete", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Delete", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/report/{idStudent}/{idCourse}")
	public ResponseEntity<?> getList(@PathVariable String idStudent, @PathVariable String idCourse){
		try {
			studentService.cetakReportStudent(idStudent, idCourse);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
