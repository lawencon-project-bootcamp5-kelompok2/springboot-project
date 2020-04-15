package com.lawencon.app.springbootproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.dao.SoalDao;
import com.lawencon.app.springbootproject.model.Soal;
import com.lawencon.app.springbootproject.service.SoalService;

@Service
@Transactional
public class SoalServiceImpl implements SoalService{
	
	@Autowired
	private SoalDao soalDao;

	@Override
	public Soal upload(MultipartFile materi) throws Exception {
		return soalDao.upload(materi);
	}

	@Override
	public Soal getFile(String fileId) throws Exception {
		return soalDao.getFile(fileId);
	}

}
