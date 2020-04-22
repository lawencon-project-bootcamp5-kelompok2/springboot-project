package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.dao.MateriDao;
import com.lawencon.app.springbootproject.model.Materi;
import com.lawencon.app.springbootproject.service.MateriService;

@Service
@Transactional
public class MateriServiceImpl implements MateriService{
	
	@Autowired
	private MateriDao materiDao;

	@Override
	public Materi upload(MultipartFile materi) throws Exception {
		return materiDao.upload(materi);
	}

	@Override
	public Materi getFile(String fileId) throws Exception {
		return materiDao.getFile(fileId);
	}

	@Override
	public List<?> findAll() throws Exception {
		return materiDao.findAll();
	}

}
