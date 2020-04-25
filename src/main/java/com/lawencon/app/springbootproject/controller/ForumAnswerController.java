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

import com.lawencon.app.springbootproject.model.ForumAnswer;
import com.lawencon.app.springbootproject.service.ForumAnswerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/forum/answer")
public class ForumAnswerController extends BaseController{

	@Autowired
	private ForumAnswerService forumAnswerService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER')")
	public ResponseEntity<?> getList(){
		List<?> listAnswer = new ArrayList<>();
		try {
			listAnswer = forumAnswerService.findAll();
			return new ResponseEntity<>(listAnswer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listAnswer, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/{idAnswer}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER')")
	public ResponseEntity<?> getListId(@PathVariable("idAnswer") String idAnswer){
		try {
			forumAnswerService.findById(idAnswer);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER')")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			ForumAnswer forumAnswer = readValue(content, ForumAnswer.class);
			forumAnswerService.createForumAnswer(forumAnswer);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER')")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			ForumAnswer forumAnswer = readValue(content, ForumAnswer.class);
			forumAnswerService.update(forumAnswer);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER')")
	public ResponseEntity<?> getDelete(@PathVariable("idAnswer") String idAnswer){
		try {
			forumAnswerService.delete(idAnswer);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
}
