package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;
import com.lawencon.app.springbootproject.payload.response.MessageResponse;
import com.lawencon.app.springbootproject.service.StudentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController extends BaseController{

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListId(@PathVariable("idStudent") String idStudent){
		try {
			Student student = studentService.findById(idStudent);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/email/{email}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListByEmail(@PathVariable("email") String email){
		try {
			Student student = studentService.findByEmail(email);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<?> getInsert(@Valid @RequestBody SignupRequest signUpRequest){
		try {
			if(studentService.validStudent(signUpRequest)==true) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error: Data Already Exist"));
			}
			return new ResponseEntity<>(studentService.createStudents(signUpRequest), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Student student = readValue(content, Student.class);
			studentService.updateStudent(student);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/profil")
	@PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
	public ResponseEntity<?> getUpdateProfil(@RequestBody String content){
		try {
			Student student = readValue(content, Student.class);
			studentService.updateProfil(student);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idStudent}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
	public ResponseEntity<?> getDelete(@PathVariable("idStudent") String idStudent){
		try {
			studentService.deleteStudent(idStudent);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/report/{idStudent}/{idKelas}")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<byte[]> getList(@PathVariable String idStudent, @PathVariable String idKelas){
		try {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(MediaType.APPLICATION_PDF);
			responseHeaders.add("content-disposition", "inline;filename='report'");
			studentService.cetakReportStudent(idStudent, idKelas);
			return new ResponseEntity<>(studentService.cetakReportStudent(idStudent, idKelas), responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
