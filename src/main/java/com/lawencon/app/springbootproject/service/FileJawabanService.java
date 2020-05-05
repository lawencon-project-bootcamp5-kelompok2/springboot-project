package com.lawencon.app.springbootproject.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.FileJawaban;

public interface FileJawabanService {
	FileJawaban upload(MultipartFile fileJawaban) throws Exception;
	FileJawaban getFile(String fileId) throws Exception;
	List<?> findAll() throws Exception;
}
