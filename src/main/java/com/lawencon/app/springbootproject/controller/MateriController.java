package com.lawencon.app.springbootproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.Materi;
import com.lawencon.app.springbootproject.service.MateriService;

@RestController
@CrossOrigin("*")
@RequestMapping("/materi")
public class MateriController extends BaseController {
	
	@Autowired
	private MateriService materiService;
	
//	### First Version ###
//	@PostMapping("/insert")
//	@PreAuthorize("hasRole('TRAINER')")
//	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
//		try {
//			Materi materi = materiService.upload(file);
//			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//	                .path("/file/downloadFile/")
//	                .path(materi.getIdMateri())
//	                .toUriString();
//			return new UploadFileResponse(materi.getFileName(), fileDownloadUri,
//	                file.getContentType(), file.getSize());
//			return new ResponseEntity<>(materi, HttpStatus.OK);
//		} catch (Exception e) {
//			return null;
//		}
//	}
	
	@PostMapping("/uploadFile")
	@PreAuthorize("hasRole('TRAINER')")
	public ResponseEntity<?> getInsert(@RequestParam("file") MultipartFile file){
		try {
			Materi materi = materiService.upload(file);
			return new ResponseEntity<>(materi, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('STUDENT') or hasRole('TRAINER') or hasRole('ADMIN')")
	public ResponseEntity<?> getList(){
		List<?> listMateri = new ArrayList<>();
		try {
			listMateri = materiService.findAll();
			return new ResponseEntity<>(listMateri, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listMateri, HttpStatus.BAD_REQUEST);
		}
	}
	
    @GetMapping("/downloadFile/{fileId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
    	try {
            Materi materi = materiService.getFile(fileId);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(materi.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + materi.getFileName() + "\"")
                    .body(new ByteArrayResource(materi.getData()));
		} catch (Exception e) {
			return null;
		}
    }
}
