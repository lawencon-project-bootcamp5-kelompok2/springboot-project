package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.TestDao;
import com.lawencon.app.springbootproject.model.Test;
import com.lawencon.app.springbootproject.service.TestService;

@Service
@Transactional
public class TestServiceImpl implements TestService{
	
	@Autowired
	private TestDao testDao;

	@Override
	public List<?> findAll() throws Exception {
		return testDao.findAll();
	}

	@Override
	public Test findById(String idTest) throws Exception {
		return testDao.findById(idTest);
	}

	@Override
	public void insert(Test test) throws Exception {
		testDao.insert(test);
	}

	@Override
	public Test update(Test test) throws Exception {
		return testDao.update(test);
	}

	@Override
	public void delete(String idTest) throws Exception {
		testDao.delete(idTest);
	}

	@Override
	public Boolean findWaktuSelesai(String idTest) throws Exception {
		List<?> listWaktuSelesai = null;
		try {
			listWaktuSelesai = testDao.findWaktuSelesai(idTest);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(listWaktuSelesai != null) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean cekTest(Test test) throws Exception {
		Test t = null;
		try {
			t = testDao.cekTest(test);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(t!=null) {
			return true;
		}
		return false;
	}

	@Override
	public String getIdTestByKelas(String idKelas) throws Exception {
		return testDao.getIdTestByKelas(idKelas);
	}
}
