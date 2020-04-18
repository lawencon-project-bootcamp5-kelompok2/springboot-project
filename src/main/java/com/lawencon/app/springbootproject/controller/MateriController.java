package com.lawencon.app.springbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

import com.lawencon.app.springbootproject.model.Materi;
import com.lawencon.app.springbootproject.model.UploadFileResponse;
import com.lawencon.app.springbootproject.service.MateriService;

@RestController
@CrossOrigin("*")
@RequestMapping("/materi")
public class MateriController extends BaseController<Materi> {
	
	@Autowired
	private MateriService materiService;
	
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			Materi materi = materiService.upload(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/file/downloadFile/")
	                .path(materi.getIdMateri())
	                .toUriString();
			return new UploadFileResponse(materi.getFileName(), fileDownloadUri,
	                file.getContentType(), file.getSize());
		} catch (Exception e) {
			return null;
		}
	}
	
    @GetMapping("/downloadFile/{fileId}")
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
