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

import com.lawencon.app.springbootproject.model.Kelas;
import com.lawencon.app.springbootproject.service.KelasService;

@RestController
@CrossOrigin("*")
@RequestMapping("/kelas")
public class KelasController extends BaseController{

	@Autowired
	private KelasService kelasService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getList(){
		List<?> listKelas = new ArrayList<>();
		try {
			listKelas = kelasService.findAll();
			return new ResponseEntity<>(listKelas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listKelas, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/{idKelas}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListId(@PathVariable("idKelas") String idKelas){
		try {
			kelasService.findById(idKelas);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Kelas kelas = readValue(content, Kelas.class);
			return new ResponseEntity<>(kelasService.insert(kelas), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Insert", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Kelas kelas = readValue(content, Kelas.class);
			kelasService.update(kelas);
			return new ResponseEntity<>("Success Update", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Update", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idKelas}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getDelete(@PathVariable("idKelas") String idKelas){
		try {
			kelasService.delete(idKelas);
			return new ResponseEntity<>("Success Delete", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Delete", HttpStatus.BAD_REQUEST);
		}
	}
}
