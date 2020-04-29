package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import com.lawencon.app.springbootproject.model.Trainer;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;
import com.lawencon.app.springbootproject.service.TrainerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/trainer")
public class TrainerController extends BaseController {


	@Autowired
	private TrainerService trainerService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
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
	
	@GetMapping("/search/{idTrainer}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListId(@PathVariable("idTrainer") String idTrainer){
		try {
			Trainer trainer = trainerService.findById(idTrainer);
			return new ResponseEntity<>(trainer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/email/{email}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListByEmail(@PathVariable("email") String email){
		try {
			Trainer trainer = trainerService.findByEmail(email);
			return new ResponseEntity<>(trainer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search-keyword/{search}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getByKeyword(@PathVariable("search") String search){
		List<Trainer> trainer = new ArrayList<>();
		try {
			trainer = trainerService.findByNamaAndEmail(search);
			return new ResponseEntity<>(trainer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getInsert(@Valid @RequestBody SignupRequest signUpRequest){
		try {
			return new ResponseEntity<>(trainerService.createTrainers(signUpRequest), HttpStatus.FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed insert", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Trainer trainer = readValue(content, Trainer.class);
			trainerService.updateTrainer(trainer);
			return new ResponseEntity<>(trainer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed update", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idTrainer}")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getDelete(@PathVariable("idTrainer") String idTrainer){
		try {
			trainerService.deleteTrainer(idTrainer);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/report/{idTrainer}/{idSubcourse}")
	@PreAuthorize("hasRole('TRAINER')")
	public ResponseEntity<?> getList(@PathVariable String idTrainer, @PathVariable String idSubcourse){
		try {
			trainerService.cetakReportTrainer(idTrainer, idSubcourse);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
