package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.app.springbootproject.dao.PertemuanDao;
import com.lawencon.app.springbootproject.model.Pertemuan;
import com.lawencon.app.springbootproject.service.PertemuanService;

@Service
@Transactional
public class PertemuanServiceImpl implements PertemuanService{

	@Autowired
	private PertemuanDao pertemuanDao;

	@Override
	public List<?> findAll() throws Exception {
		return pertemuanDao.findAll();
	}

	@Override
	public Pertemuan findById(String idPertemuan) throws Exception {
		return pertemuanDao.findById(idPertemuan);
	}

	@Override
	public String insert(Pertemuan pertemuan) throws Exception {
		if(validPertemuan(pertemuan)==true) {
			throw new Exception("Error: Data Already Exist");
		}
		else {
			pertemuanDao.insert(pertemuan);
			return "Success Insert";
		}
	}

	@Override
	public Pertemuan update(Pertemuan pertemuan) throws Exception {
		validate(pertemuan.getIdPertemuan());
		return pertemuanDao.update(pertemuan);
	}

	@Override
	public void delete(String idPertemuan) throws Exception {
		validate(idPertemuan);
		pertemuanDao.delete(idPertemuan);
	}

	@Override
	public Boolean validPertemuan(Pertemuan pertemuan) throws Exception {
		Pertemuan p = null;
		try {
			p = pertemuanDao.validPertemuan(pertemuan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(p != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<?> findBySubcourse(String idSubcourse) throws Exception {
		return pertemuanDao.findBySubcourse(idSubcourse);
	}

	@Override
	public void validate(String idPertemuan) throws Exception {
		try {
			pertemuanDao.findById(idPertemuan);
		} catch (NoResultException e) {
			throw new Exception("Wrong id");
		}
	}
	
}
