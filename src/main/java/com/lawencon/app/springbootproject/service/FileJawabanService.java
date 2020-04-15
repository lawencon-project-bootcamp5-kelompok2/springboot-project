package com.lawencon.app.springbootproject.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.FileJawaban;

public interface FileJawabanService {
	abstract FileJawaban upload(MultipartFile materi) throws Exception;
	abstract FileJawaban getFile(String fileId) throws Exception;
}
