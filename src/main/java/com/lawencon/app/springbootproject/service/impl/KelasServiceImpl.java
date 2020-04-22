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
	public Kelas findById(String idKelas) throws Exception {
		return kelasDao.findById(idKelas);
	}

	@Override
	public String insert(Kelas kelas) throws Exception {
		if(validKelas(kelas)==true) {
			return "Class already exist on that schedule";
		}
		else {
			kelasDao.insert(kelas);
		}
		return "Success";
	}

	@Override
	public void update(Kelas kelas) throws Exception {
		kelasDao.update(kelas);
	}

	@Override
	public void delete(String idKelas) throws Exception {
		kelasDao.delete(idKelas);
	}

	@Override
	public Boolean validKelas(Kelas kelas) throws Exception {
		Kelas k = null;
		try {
			k = kelasDao.validKelas(kelas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(k != null) {
			return true;
		}
		return false;
	}
}