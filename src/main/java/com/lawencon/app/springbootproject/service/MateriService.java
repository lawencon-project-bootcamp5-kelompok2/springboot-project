package com.lawencon.app.springbootproject.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.Materi;

public interface MateriService {
	abstract Materi upload(MultipartFile materi) throws Exception;
	abstract Materi getFile(String fileId) throws Exception;
}
