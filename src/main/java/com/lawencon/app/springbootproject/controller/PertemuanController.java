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

import com.lawencon.app.springbootproject.model.Pertemuan;
import com.lawencon.app.springbootproject.service.PertemuanService;

@RestController
@CrossOrigin("*")
@RequestMapping("/pertemuan")
public class PertemuanController extends BaseController {

	@Autowired
	private PertemuanService pertemuanService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<?> listPertemuan = new ArrayList<>();
		try {
			listPertemuan = pertemuanService.findAll();
			return new ResponseEntity<>(listPertemuan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listPertemuan, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/{idPertemuan}")
	public ResponseEntity<?> getListId(@PathVariable("idPertemuan") String idPertemuan){
		try {
			pertemuanService.findById(idPertemuan);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Pertemuan pertemuan = readValue(content, Pertemuan.class);
			return new ResponseEntity<>(pertemuanService.insert(pertemuan), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Insert", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Pertemuan pertemuan = readValue(content, Pertemuan.class);
			pertemuanService.update(pertemuan);
			return new ResponseEntity<>("Success Update", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Insert", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idPertemuan}")
	public ResponseEntity<?> getDelete(@PathVariable("idPertemuan") String idPertemuan){
		try {
			pertemuanService.delete(idPertemuan);
			return new ResponseEntity<>("Success Delete", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Delete", HttpStatus.BAD_REQUEST);
		}
	}
}
