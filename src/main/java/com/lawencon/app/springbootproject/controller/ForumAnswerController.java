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

import com.lawencon.app.springbootproject.model.ForumAnswer;
import com.lawencon.app.springbootproject.service.ForumAnswerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/forum/answer")
public class ForumAnswerController extends BaseController<ForumAnswer>{

	@Autowired
	private ForumAnswerService forumAnswerService;
	
	@GetMapping("/list")
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
	
	@GetMapping("/search")
	public ResponseEntity<?> getListId(@RequestBody String content){
		try {
			ForumAnswer forumAnswer = readValue(content, ForumAnswer.class);
			forumAnswerService.findById(forumAnswer);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/create")
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
	
	@GetMapping("/update")
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
	
	@GetMapping("/delete")
	public ResponseEntity<?> getDelete(@RequestBody String content){
		try {
			ForumAnswer forumAnswer = readValue(content, ForumAnswer.class);
			forumAnswerService.delete(forumAnswer);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
}
