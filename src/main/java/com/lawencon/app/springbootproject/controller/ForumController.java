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

import com.lawencon.app.springbootproject.model.Forum;
import com.lawencon.app.springbootproject.service.ForumService;

@RestController
@CrossOrigin("*")
@RequestMapping("/forum")
public class ForumController extends BaseController{

	@Autowired
	private ForumService forumService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER')")
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
	
	@GetMapping("/search/{idForum}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER')")
	public ResponseEntity<?> getListId(@PathVariable("idForum") String idForum){
		try {
			Forum forum = forumService.findById(idForum);
			return new ResponseEntity<>(forum, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/pertemuan/{idPertemuan}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER')")
	public ResponseEntity<?> getForumByIdSubcourse(@PathVariable("idPertemuan") String idPertemuan){
		List<?> listPertemuan = new ArrayList<>();
		try {
			listPertemuan = forumService.findByIdPertemuan(idPertemuan);
			return new ResponseEntity<>(listPertemuan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listPertemuan, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER')")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Forum forum = readValue(content, Forum.class);
			forumService.createForum(forum);
			return new ResponseEntity<>(forum, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER')")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Forum forum = readValue(content, Forum.class);
			forumService.update(forum);
			return new ResponseEntity<>(forum, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idForum}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER')")
	public ResponseEntity<?> getDelete(@PathVariable("idForum") String idForum){
		try {
			forumService.delete(idForum);
			return new ResponseEntity<>("Success Delete", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
