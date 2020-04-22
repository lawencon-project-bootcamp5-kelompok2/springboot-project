package com.lawencon.app.springbootproject.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.Materi;

public interface MateriDao {
	abstract Materi upload(MultipartFile materi) throws Exception;
	abstract Materi getFile(String fileId) throws Exception;
	abstract List<?> findAll()throws Exception;
}
