package com.lawencon.app.springbootproject.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.Soal;

public interface SoalService {
	abstract Soal upload(MultipartFile soal) throws Exception;
	abstract Soal getFile(String fileId) throws Exception;
	abstract List<?> findAll() throws Exception;
}
