package com.lawencon.app.springbootproject.service;

import java.util.List;

public interface NilaiMeanKelasService {
	List<Integer> listMean(String idCourse)throws Exception;
	List<?> listMeanOfSubcourseByCourse(String idCourse)throws Exception;
}
