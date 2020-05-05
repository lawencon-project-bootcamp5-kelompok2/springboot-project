package com.lawencon.app.springbootproject.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.Materi;

public interface MateriService {
	Materi upload(MultipartFile materi) throws Exception;
	Materi getFile(String fileId) throws Exception;
	List<?> findAll() throws Exception;
}
