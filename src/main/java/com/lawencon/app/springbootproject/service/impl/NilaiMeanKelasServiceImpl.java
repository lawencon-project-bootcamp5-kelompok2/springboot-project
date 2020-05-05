package com.lawencon.app.springbootproject.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.app.springbootproject.dao.NilaiMeanKelasDao;
import com.lawencon.app.springbootproject.service.NilaiMeanKelasService;

@Service
@Transactional
public class NilaiMeanKelasServiceImpl implements NilaiMeanKelasService{

	@Autowired
	private NilaiMeanKelasDao nilaiMeanDao;

	@Override
	public List<Integer> listMean(String idCourse) throws Exception {
		List<Map<String, Object>> listMean = nilaiMeanDao.listMean(idCourse);
		List<Integer> listValue = new ArrayList<>();
		if(!listMean.isEmpty()) {
			listValue.add(Integer.valueOf(listMean.get(0).get("Mean").toString()));
		}
		return listValue;
	}

	@Override
	public List<?> listMeanOfSubcourseByCourse(String idCourse) throws Exception {
		return nilaiMeanDao.listMeanOfSubcourseByCourse(idCourse);
	}
}
