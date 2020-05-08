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

import com.lawencon.app.springbootproject.model.Pertemuan;
import com.lawencon.app.springbootproject.model.payload.response.MessageResponse;
import com.lawencon.app.springbootproject.service.PertemuanService;

@RestController
@CrossOrigin("*")
@RequestMapping("/pertemuan")
public class PertemuanController extends BaseController {

	@Autowired
	private PertemuanService pertemuanService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListId(@PathVariable("idPertemuan") String idPertemuan){
		try {
			Pertemuan pertemuan = pertemuanService.findById(idPertemuan);
			return new ResponseEntity<>(pertemuan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/pertemuan/{idSubcourse}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListSubcourse(@PathVariable("idSubcourse") String idSubcourse){
		List<?> listSubcourse = new ArrayList<>();
		try {
			listSubcourse = pertemuanService.findBySubcourse(idSubcourse);
			return new ResponseEntity<>(listSubcourse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Pertemuan pertemuan = readValue(content, Pertemuan.class);
			pertemuanService.insert(pertemuan);
			return new ResponseEntity<>(pertemuan, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
		}
			
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Pertemuan pertemuan = readValue(content, Pertemuan.class);
			pertemuanService.update(pertemuan);
			return new ResponseEntity<>(pertemuan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idPertemuan}")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getDelete(@PathVariable("idPertemuan") String idPertemuan){
		try {
			pertemuanService.delete(idPertemuan);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
