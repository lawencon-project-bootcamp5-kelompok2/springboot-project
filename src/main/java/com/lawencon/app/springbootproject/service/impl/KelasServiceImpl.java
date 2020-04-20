package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.app.springbootproject.dao.KelasDao;
import com.lawencon.app.springbootproject.model.Kelas;
import com.lawencon.app.springbootproject.service.KelasService;

@Service
@Transactional
public class KelasServiceImpl implements KelasService{

	@Autowired
	private KelasDao kelasDao;

	@Override
	public List<?> findAll() throws Exception {
		return kelasDao.findAll();
	}

	@Override
	public Kelas findById(Kelas kelas) throws Exception {
		return kelasDao.findById(kelas);
	}

	@Override
	public void insert(Kelas kelas) throws Exception {
		kelasDao.insert(kelas);
	}

	@Override
	public void update(Kelas kelas) throws Exception {
		kelasDao.update(kelas);
	}

	@Override
	public void delete(Kelas kelas) throws Exception {
		kelasDao.delete(kelas);
	}
}
