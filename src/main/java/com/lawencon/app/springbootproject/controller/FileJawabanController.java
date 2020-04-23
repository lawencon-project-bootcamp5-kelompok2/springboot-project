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

import com.lawencon.app.springbootproject.model.FileJawaban;
import com.lawencon.app.springbootproject.model.UploadFileResponse;
import com.lawencon.app.springbootproject.service.FileJawabanService;

@RestController
@CrossOrigin("*")
@RequestMapping("/fileJawaban")
public class FileJawabanController extends BaseController {
	
	@Autowired
	private FileJawabanService fileJawabanService;
	
	@PostMapping("/insert")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			FileJawaban fileJawaban = fileJawabanService.upload(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/file/downloadFile/")
	                .path(fileJawaban.getIdFileJawaban())
	                .toUriString();
			return new UploadFileResponse(fileJawaban.getFileName(), fileDownloadUri,
	                file.getContentType(), file.getSize());
		} catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping("/uploadFile")
	public ResponseEntity<?> getInsert(@RequestParam("file") MultipartFile file){
		try {
			fileJawabanService.upload(file);
			return new ResponseEntity<>("Success Upload", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed Upload", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<?> listFileJawaban = new ArrayList<>();
		try {
			listFileJawaban = fileJawabanService.findAll();
			return new ResponseEntity<>(listFileJawaban, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listFileJawaban, HttpStatus.BAD_REQUEST);
		}
	}
	
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
    	try {
            FileJawaban fileJawaban = fileJawabanService.getFile(fileId);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileJawaban.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileJawaban.getFileName() + "\"")
                    .body(new ByteArrayResource(fileJawaban.getData()));
		} catch (Exception e) {
			return null;
		}
    }
}
