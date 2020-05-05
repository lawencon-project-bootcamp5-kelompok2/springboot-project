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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.app.springbootproject.model.Absensi;
import com.lawencon.app.springbootproject.payload.response.MessageResponse;
import com.lawencon.app.springbootproject.service.AbsensiService;

@RestController
@CrossOrigin("*")
@RequestMapping("/absensi")
public class AbsensiController extends BaseController{

	@Autowired
	private AbsensiService absensiService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
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
	
	@GetMapping("/detail/{idSubcourse}/{idKelas}")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getDetailAbsen(@PathVariable("idSubcourse") String idSubcourse, @PathVariable("idKelas") String idKelas){
		List<?> listAbsenStudent = new ArrayList<>();
		try {
			listAbsenStudent = absensiService.findPending(idSubcourse, idKelas);
			return new ResponseEntity<>(listAbsenStudent, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listAbsenStudent, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/{idStudent}")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListId(@PathVariable("idStudent") String idStudent){
		List<?> listAbsenStudent = new ArrayList<>();
		try {
			listAbsenStudent = absensiService.findByStudent(idStudent);
			return new ResponseEntity<>(listAbsenStudent, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listAbsenStudent, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/pertemuan/{idPertemuan}/{emailStudent}")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListIdPertemuan(@PathVariable("idPertemuan") String idPertemuan, @PathVariable("emailStudent")String emailStudent){
		List<?> listAbsenStudent = new ArrayList<>();
		try {
			listAbsenStudent = absensiService.findByIdPertemuanAndStudent(idPertemuan, emailStudent);
			return new ResponseEntity<>(listAbsenStudent, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listAbsenStudent, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getInsert(@RequestBody String content) throws Exception {
		Absensi absensi = readValue(content, Absensi.class);
		if (absensiService.cekAbsen(absensi) == true) {
			return ResponseEntity.badRequest().body(new MessageResponse("please wait the confirmation by trainer!"));
		}
		absensiService.insert(absensi);
		return ResponseEntity.ok().body(new MessageResponse("Success Insert"));
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Absensi absensi = readValue(content, Absensi.class);
			absensiService.update(absensi);
			return new ResponseEntity<>(absensi, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/report/{idKelas}/{idPertemuan}")
	@PreAuthorize("hasRole('TRAINER')")
	public ResponseEntity<byte[]> getReport(@PathVariable String idKelas, @PathVariable String idPertemuan){
		try {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(MediaType.APPLICATION_PDF);
			responseHeaders.add("content-disposition", "inline;filename='report'");
			absensiService.cetakAbsen(idKelas, idPertemuan);
			return new ResponseEntity<>(absensiService.cetakAbsen(idKelas, idPertemuan), responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
