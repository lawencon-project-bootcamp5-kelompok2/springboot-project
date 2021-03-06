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

import com.lawencon.app.springbootproject.model.Subcourse;
import com.lawencon.app.springbootproject.model.payload.response.MessageResponse;
import com.lawencon.app.springbootproject.service.SubcourseService;

@RestController
@CrossOrigin("*")
@RequestMapping("/subcourse")
public class SubcourseController extends BaseController{

	@Autowired
	private SubcourseService subcourseService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getList(){
		List<?> listCourse = new ArrayList<>();
		try {
			listCourse = subcourseService.findAll();
			return new ResponseEntity<>(listCourse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listCourse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/{idSubcourse}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListId(@PathVariable("idSubcourse") String idSubcourse){
		try {
			Subcourse subcourse = subcourseService.findById(idSubcourse);
			return new ResponseEntity<>(subcourse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/tanggalSelesai/{idSubcourse}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getTanggal(@PathVariable("idSubcourse") String idSubcourse){
		try {
			List<?> subcourse = subcourseService.findTanggalSelesai(idSubcourse);
			return new ResponseEntity<>(subcourse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/course/{namaCourse}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getListCourse(@PathVariable("namaCourse") String namaCourse){
		List<?> listCourse = new ArrayList<>();
		try {
			listCourse = subcourseService.findByCourse(namaCourse);
			return new ResponseEntity<>(listCourse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/kelas/{idKelas}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getByKelas(@PathVariable("idKelas") String idKelas){
		List<?> listData = new ArrayList<>();
		try {
			listData = subcourseService.findByKelas(idKelas);
			return new ResponseEntity<>(listData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/nilai/{idSubcourse}/{idKelas}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getNilai(@PathVariable("idSubcourse") String idSubcourse, @PathVariable("idKelas") String idKelas){
		List<?> listCourse = new ArrayList<>();
		try {
			listCourse = subcourseService.tampilanLihatNilai(idSubcourse, idKelas);
			return new ResponseEntity<>(listCourse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listCourse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/inputnilai/{idSubcourse}/{idKelas}")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getInputNilai(@PathVariable("idSubcourse") String idSubcourse, @PathVariable("idKelas") String idKelas){
		List<?> listCourse = new ArrayList<>();
		try {
			listCourse = subcourseService.tampilanInputNilai(idSubcourse, idKelas);
			return new ResponseEntity<>(listCourse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listCourse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/insert")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getInsert(@RequestBody String content){
		try {
			Subcourse subcourse = readValue(content, Subcourse.class);
			subcourseService.insert(subcourse);
			return new ResponseEntity<>(subcourse, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
		}
		
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getUpdate(@RequestBody String content){
		try {
			Subcourse subcourse = readValue(content, Subcourse.class);
			subcourseService.update(subcourse);
			return new ResponseEntity<>(subcourse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{idSubcourse}")
	@PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getDelete(@PathVariable("idSubcourse") String idSubcourse){
		try {
			subcourseService.delete(idSubcourse);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
