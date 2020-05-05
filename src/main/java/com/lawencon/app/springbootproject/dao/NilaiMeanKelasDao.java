package com.lawencon.app.springbootproject.dao;

import java.util.List;
import java.util.Map;

public interface NilaiMeanKelasDao {
	List<Map<String, Object>> listMean(String idCourse)throws Exception;
	List<?> listMeanOfSubcourseByCourse(String idCourse)throws Exception;
}
