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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.app.springbootproject.model.Trainer;
import com.lawencon.app.springbootproject.service.TrainerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/trainer")
public class TrainerController extends BaseController {


	@Autowired
	private TrainerService trainerService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<Trainer> listTrainer = new ArrayList<>();
		try {
			listTrainer = trainerService.findAll();
			return new ResponseEntity<>(listTrainer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listTrainer, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getListId(@RequestBody String content){
		try {
			Trainer trainer = readValue(content, Trainer.class);
			trainerService.findById(trainer);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Trainer trainer = readValue(content, Trainer.class);
			return new ResponseEntity<>(trainerService.createTrainer(trainer), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed insert", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Trainer trainer = readValue(content, Trainer.class);
			trainerService.updateTrainer(trainer);
			return new ResponseEntity<>("Success update", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed update", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> getDelete(@RequestBody String content){
		try {
			Trainer trainer = readValue(content, Trainer.class);
			trainerService.deleteTrainer(trainer);
			return new ResponseEntity<>("Success delete", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed delete", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/report/{id}")
	public ResponseEntity<?> getList(@PathVariable String id){
		try {
			trainerService.cetakReportTrainer(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
