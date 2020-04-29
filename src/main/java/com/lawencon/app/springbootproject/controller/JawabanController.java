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

import com.lawencon.app.springbootproject.model.Jawaban;
import com.lawencon.app.springbootproject.service.JawabanService;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class JawabanController extends BaseController {

	@Autowired
	private JawabanService jawabanService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('TRAINER')")
	public ResponseEntity<?> getList(){
		List<?> listJawaban = new ArrayList<>();
		try {
			listJawaban = jawabanService.findAll();
			return new ResponseEntity<>(listJawaban, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listJawaban, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/{idJawaban}")
	@PreAuthorize("hasRole('TRAINER')")
	public ResponseEntity<?> getListId(@PathVariable("idJawaban") String idJawaban){
		try {
			Jawaban jawaban = jawabanService.findById(idJawaban);
			return new ResponseEntity<>(jawaban, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Jawaban jawaban = readValue(content, Jawaban.class);
			jawabanService.insert(jawaban);
			return new ResponseEntity<>(jawaban, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Insert", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Jawaban jawaban = readValue(content, Jawaban.class);
			jawabanService.update(jawaban);
			return new ResponseEntity<>(jawaban, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Update", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idJawaban}")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<?> getDelete(@PathVariable("idJawaban") String idJawaban){
		try {
			jawabanService.delete(idJawaban);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Delete", HttpStatus.BAD_REQUEST);
		}
	}
}
