package com.lawencon.app.springbootproject.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.FileJawaban;

public interface FileJawabanService {
	abstract FileJawaban upload(MultipartFile fileJawaban) throws Exception;
	abstract FileJawaban getFile(String fileId) throws Exception;
	abstract List<?> findAll() throws Exception;
	abstract FileJawaban validTimer(FileJawaban uploadTime)throws Exception;
}
