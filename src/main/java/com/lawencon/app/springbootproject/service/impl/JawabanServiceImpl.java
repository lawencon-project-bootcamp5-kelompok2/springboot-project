package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.JawabanDao;
import com.lawencon.app.springbootproject.model.Jawaban;
import com.lawencon.app.springbootproject.service.JawabanService;

@Service
@Transactional
public class JawabanServiceImpl implements JawabanService{

	@Autowired
	private JawabanDao jawabanDao;

	@Override
	public List<?> findAll() throws Exception {
		return jawabanDao.findAll();
	}

	@Override
	public Jawaban findById(String idJawaban) throws Exception {
		return jawabanDao.findById(idJawaban);
	}

	@Override
	public void insert(Jawaban jawaban) throws Exception {
		jawabanDao.insert(jawaban);
	}

	@Override
	public Jawaban update(Jawaban jawaban) throws Exception {
		return jawabanDao.update(jawaban);
	}

	@Override
	public void delete(String idJawaban) throws Exception {
		jawabanDao.delete(idJawaban);
	}

}
