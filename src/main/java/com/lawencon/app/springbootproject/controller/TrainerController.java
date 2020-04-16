package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.app.springbootproject.service.TrainerService;

@RestController
@CrossOrigin("*")
public class TrainerController {

	@Autowired
	private TrainerService trainerService;
	
	@GetMapping("/trainerreport/{id}")
	public ResponseEntity<?> getList(@PathVariable String id){
		List<?> data = new ArrayList<>();
		try {
			data = trainerService.cetakReportTrainer(id);
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
		}
	}
}
