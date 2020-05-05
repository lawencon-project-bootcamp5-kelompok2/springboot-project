package com.lawencon.app.springbootproject.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.Soal;

public interface SoalDao {
	Soal upload(MultipartFile soal) throws Exception;
	Soal getFile(String fileId) throws Exception;
	List<?> findAll() throws Exception;
}
