package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.app.springbootproject.dao.AbsensiDao;
import com.lawencon.app.springbootproject.model.Absensi;
import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.service.AbsensiService;

@Service
@Transactional
public class AbsensiServiceImpl implements AbsensiService {
	
	@Autowired
	private AbsensiDao absensiDao;

	@Override
	public void insert(Absensi absensi) throws Exception{
		absensiDao.insert(absensi);
	}

	@Override
	public List<Absensi> findAll() throws Exception{
		return absensiDao.findAll();
	}

	@Override
	public Absensi findByStudent(Student id) throws Exception{
		return absensiDao.findByStudent(id);
	}

	@Override
	public Absensi update(Absensi absensi) throws Exception {
		return absensiDao.update(absensi);
	}
}
