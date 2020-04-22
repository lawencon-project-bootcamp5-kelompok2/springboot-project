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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lawencon.app.springbootproject.model.Soal;
import com.lawencon.app.springbootproject.model.UploadFileResponse;
import com.lawencon.app.springbootproject.service.SoalService;

@RestController
@CrossOrigin("*")
@RequestMapping("/soal")
public class SoalController extends BaseController {
	
	@Autowired
	private SoalService soalService;
	
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			Soal soal = soalService.upload(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/file/downloadFile/")
	                .path(soal.getIdSoal())
	                .toUriString();
			return new UploadFileResponse(soal.getFileName(), fileDownloadUri,
	                file.getContentType(), file.getSize());
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<?> listSoal = new ArrayList<>();
		try {
			listSoal = soalService.findAll();
			return new ResponseEntity<>(listSoal, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listSoal, HttpStatus.BAD_REQUEST);
		}
	}
	
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
    	try {
            Soal soal = soalService.getFile(fileId);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(soal.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + soal.getFileName() + "\"")
                    .body(new ByteArrayResource(soal.getData()));
		} catch (Exception e) {
			return null;
		}
    }
}
