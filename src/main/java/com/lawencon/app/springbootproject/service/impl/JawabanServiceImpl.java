package com.lawencon.app.springbootproject.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

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
		validate(jawaban.getIdJawaban());
		return jawabanDao.update(jawaban);
	}

	@Override
	public void delete(String idJawaban) throws Exception {
		validate(idJawaban);
		jawabanDao.delete(idJawaban);
	}

	@Override
	public List<?> findResultStudentFromSubcourse(String idTest, String idStudent) throws Exception {
		return jawabanDao.findResultStudentFromSubcourse(idTest, idStudent);
	}

	@Override
	public List<Integer> findAverageStudentFromSubcourse(String idTest) throws Exception {
		List<Map<String, Object>> listMeanFromSubcourse = jawabanDao.findAverageStudentFromSubcourse(idTest);
		List<Integer> listValue = new ArrayList<>();
		if(!listMeanFromSubcourse.isEmpty()) {
			listValue.add(Integer.valueOf(listMeanFromSubcourse.get(0).get("Mean").toString()));
		}
		return listValue;
	}

	@Override
	public List<?> findResultStudentFromAllSubcourse(String idStudent) throws Exception {
		return jawabanDao.findResultStudentFromAllSubcourse(idStudent);
	}

	@Override
	public List<Integer> findAverageStudentFromAllSubcourse(String idStudent) throws Exception {
		List<Map<String, Object>> listMeanFromAllSubcourse = jawabanDao.findAverageStudentFromAllSubcourse(idStudent);
		List<Integer> listValue = new ArrayList<>();
		if(!listMeanFromAllSubcourse.isEmpty()) {
			listValue.add(Integer.valueOf(listMeanFromAllSubcourse.get(0).get("Mean").toString()));
		}
		return listValue;
	}

	@Override
	public void validate(String idJawaban) throws Exception {
		try {
			jawabanDao.findById(idJawaban);
		} catch (NoResultException e) {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public List<?> findResultByTest(String idTest) throws Exception {
		return jawabanDao.findResultByTest(idTest);
	}
}
