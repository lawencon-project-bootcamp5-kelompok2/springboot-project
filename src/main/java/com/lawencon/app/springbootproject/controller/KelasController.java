package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.lawencon.app.springbootproject.model.Kelas;
import com.lawencon.app.springbootproject.model.payload.response.MessageResponse;
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
	
	@GetMapping("/search/available/{idStudent}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getAvailableKelas(@PathVariable("idStudent") String idStudent){
		List<?> listKelas = new ArrayList<>();
		try {
			listKelas = kelasService.findAvailableClass(idStudent);
			return new ResponseEntity<>(listKelas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/report")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<byte[]> getReport(){
		try {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(MediaType.APPLICATION_PDF);
			responseHeaders.add("content-disposition", "inline;filename='report'");
			kelasService.cetakKelas();
			return new ResponseEntity<>(kelasService.cetakKelas(), responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/{idKelas}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListId(@PathVariable("idKelas") String idKelas){
		try {
			Kelas kelas = kelasService.findById(idKelas);
			return new ResponseEntity<>(kelas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/trainer/{idTrainer}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListByTrainer(@PathVariable("idTrainer") String idTrainer){
		List<?> listData = new ArrayList<>();
		try {
			listData = kelasService.getByTrainer(idTrainer);
			return new ResponseEntity<>(listData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getInsert(@RequestBody String content) throws Exception {
		Kelas kelas = readValue(content, Kelas.class);
		if (kelasService.validKelas(kelas) == true) {
			return ResponseEntity.badRequest().body(new MessageResponse("Waktu kelas sudah dipakai"));
		}
		kelasService.insert(kelas);
		return ResponseEntity.ok().body(new MessageResponse("Success Insert"));
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Kelas kelas = readValue(content, Kelas.class);
			kelasService.update(kelas);
			return new ResponseEntity<>(kelas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idKelas}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getDelete(@PathVariable("idKelas") String idKelas){
		try {
			kelasService.delete(idKelas);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
