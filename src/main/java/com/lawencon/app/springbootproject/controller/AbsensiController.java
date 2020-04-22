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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.app.springbootproject.model.Absensi;
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
	
	@GetMapping("/search/{idStudent}")
	public ResponseEntity<?> getListId(@PathVariable("idStudent") String idStudent){
		try {
			absensiService.findByStudent(idStudent);
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
	
	@PutMapping("/update")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Absensi absensi = readValue(content, Absensi.class);
			absensiService.update(absensi);
			return new ResponseEntity<>("Success update", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed update", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/report/{idSubcourse}/{idTrainer}")
	public ResponseEntity<?> getUpdate(@PathVariable String idSubcourse, @PathVariable String idTrainer){
		try {
			absensiService.cetakAbsen(idSubcourse, idTrainer);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
