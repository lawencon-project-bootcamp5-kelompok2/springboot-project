package com.lawencon.app.springbootproject.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.Soal;

public interface SoalDao {
	abstract Soal upload(MultipartFile soal) throws Exception;
	abstract Soal getFile(String fileId) throws Exception;
	abstract List<?> findAll()throws Exception;
}
