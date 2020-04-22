package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.dao.FileJawabanDao;
import com.lawencon.app.springbootproject.model.FileJawaban;
import com.lawencon.app.springbootproject.service.FileJawabanService;

@Service
@Transactional
public class FileJawabanServiceImpl implements FileJawabanService{
	
	@Autowired
	private FileJawabanDao fileJawabanDao;

	@Override
	public FileJawaban upload(MultipartFile fileJawaban) throws Exception {
		return fileJawabanDao.upload(fileJawaban);
	}

	@Override
	public FileJawaban getFile(String fileId) throws Exception {
		return fileJawabanDao.getFile(fileId);
	}

	@Override
	public List<?> findAll() throws Exception {
		return fileJawabanDao.findAll();
	}

}
