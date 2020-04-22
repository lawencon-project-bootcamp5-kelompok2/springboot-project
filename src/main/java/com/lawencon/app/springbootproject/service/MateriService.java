package com.lawencon.app.springbootproject.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.Materi;

public interface MateriService {
	abstract Materi upload(MultipartFile materi) throws Exception;
	abstract Materi getFile(String fileId) throws Exception;
	abstract List<?> findAll() throws Exception;
}
