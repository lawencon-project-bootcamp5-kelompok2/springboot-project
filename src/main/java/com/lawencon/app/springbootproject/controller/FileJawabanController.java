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

import com.lawencon.app.springbootproject.model.FileJawaban;
import com.lawencon.app.springbootproject.model.UploadFileResponse;
import com.lawencon.app.springbootproject.service.FileJawabanService;

@RestController
@CrossOrigin("*")
@RequestMapping("/file")
public class FileJawabanController extends BaseController<FileJawaban> {
	
	@Autowired
	private FileJawabanService fileJawabanService;
	
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			FileJawaban fileJawaban = fileJawabanService.upload(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/file/downloadFile/")
	                .path(fileJawaban.getId())
	                .toUriString();
			return new UploadFileResponse(fileJawaban.getFileName(), fileDownloadUri,
	                file.getContentType(), file.getSize());
		} catch (Exception e) {
			return null;
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