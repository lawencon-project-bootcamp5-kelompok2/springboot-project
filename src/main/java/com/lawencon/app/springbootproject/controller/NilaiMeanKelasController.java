package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lawencon.app.springbootproject.service.NilaiMeanKelasService;

@RestController
@CrossOrigin("*")
@RequestMapping("/result")
public class NilaiMeanKelasController {

	@Autowired
	private NilaiMeanKelasService nilaiMeanService;
	
	@GetMapping("/list/{idCourse}")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListMean(@PathVariable("idCourse") String idCourse){
		List<?> listValue = new ArrayList<>();
		try {
			listValue = nilaiMeanService.listMeanOfSubcourseByCourse(idCourse);
			return new ResponseEntity<>(listValue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/result/mean/{idCourse}")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getResultMean(@PathVariable("idCourse") String idCourse){
		List<?> listValue = new ArrayList<>();
		try {
			listValue = nilaiMeanService.listMean(idCourse);
			return new ResponseEntity<>(listValue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
