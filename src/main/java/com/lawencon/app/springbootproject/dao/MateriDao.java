package com.lawencon.app.springbootproject.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.Materi;

public interface MateriDao {
	Materi upload(MultipartFile materi) throws Exception;
	Materi getFile(String fileId) throws Exception;
	List<?> findAll() throws Exception;
}
