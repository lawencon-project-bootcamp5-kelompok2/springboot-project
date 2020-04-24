package com.lawencon.app.springbootproject.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.model.FileJawaban;

public interface FileJawabanDao {
	abstract FileJawaban upload(MultipartFile fileJawaban) throws Exception;
	abstract FileJawaban getFile(String fileId) throws Exception;
	abstract List<?> findAll()throws Exception;
	abstract FileJawaban validTimer(FileJawaban uploadTime)throws Exception;
}
