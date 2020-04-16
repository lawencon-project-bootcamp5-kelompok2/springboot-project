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

import com.lawencon.app.springbootproject.model.Forum;
import com.lawencon.app.springbootproject.service.ForumService;

@RestController
@CrossOrigin("*")
@RequestMapping("/forum")
public class ForumController extends BaseController<Forum>{

	@Autowired
	private ForumService forumService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<?> listForum = new ArrayList<>();
		try {
			listForum = forumService.findAll();
			return new ResponseEntity<>(listForum, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listForum, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getListId(@RequestBody String content){
		try {
			Forum forum = readValue(content, Forum.class);
			forumService.findById(forum);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/insert")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Forum forum = readValue(content, Forum.class);
			forumService.createForum(forum);
			return new ResponseEntity<>("Success Insert", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Insert", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/update")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Forum forum = readValue(content, Forum.class);
			forumService.update(forum);
			return new ResponseEntity<>("Success Update", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Update", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/delete")
	public ResponseEntity<?> getDelete(@RequestBody String content){
		try {
			Forum forum = readValue(content, Forum.class);
			forumService.delete(forum);
			return new ResponseEntity<>("Success Delete", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed Delete", HttpStatus.BAD_REQUEST);
		}
	}
}