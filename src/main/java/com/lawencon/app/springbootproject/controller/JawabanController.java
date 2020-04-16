package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.app.springbootproject.model.Jawaban;
import com.lawencon.app.springbootproject.service.JawabanService;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class JawabanController extends BaseController<Jawaban> {

	@Autowired
	private JawabanService jawabanService;
	
	@GetMapping("/list")
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
	
	@GetMapping("/search")
	public ResponseEntity<?> getListId(@RequestBody String content){
		try {
			Jawaban jawaban = readValue(content, Jawaban.class);
			jawabanService.findById(jawaban);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/insert")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Jawaban jawaban = readValue(content, Jawaban.class);
			jawabanService.insert(jawaban);
			return new ResponseEntity<>("Success Insert", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Insert", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/update")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Jawaban jawaban = readValue(content, Jawaban.class);
			jawabanService.update(jawaban);
			return new ResponseEntity<>("Success Update", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Update", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/delete")
	public ResponseEntity<?> getDelete(@RequestBody String content){
		try {
			Jawaban jawaban = readValue(content, Jawaban.class);
			jawabanService.delete(jawaban);
			return new ResponseEntity<>("Success Delete", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Delete", HttpStatus.BAD_REQUEST);
		}
	}
}