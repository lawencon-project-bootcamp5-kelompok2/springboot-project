package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

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
			return "Data Already Exist";
		}
		else {
			pertemuanDao.insert(pertemuan);
		}
		return "Success Insert";
	}

	@Override
	public Pertemuan update(Pertemuan pertemuan) throws Exception {
		return pertemuanDao.update(pertemuan);
	}

	@Override
	public void delete(String idPertemuan) throws Exception {
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
	
}
