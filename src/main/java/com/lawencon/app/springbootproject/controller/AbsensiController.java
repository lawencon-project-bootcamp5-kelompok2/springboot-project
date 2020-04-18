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

import com.lawencon.app.springbootproject.model.Absensi;
import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.service.AbsensiService;

@RestController
@CrossOrigin("*")
@RequestMapping("/absensi")
public class AbsensiController extends BaseController{

	@Autowired
	private AbsensiService absensiService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<Absensi> listAbsen = new ArrayList<>();
		try {
			listAbsen = absensiService.findAll();
			return new ResponseEntity<>(listAbsen, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listAbsen, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getListId(@RequestBody String content){
		try {
			Student id = readValue(content, Student.class);
			absensiService.findByStudent(id);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Absensi absensi = readValue(content, Absensi.class);
			absensiService.insert(absensi);
			return new ResponseEntity<>("Success Insert", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Insert", HttpStatus.BAD_REQUEST);
		}
	}
}
