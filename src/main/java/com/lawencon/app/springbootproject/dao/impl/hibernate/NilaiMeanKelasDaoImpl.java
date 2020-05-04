package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.NilaiMeanKelasDao;

@Repository
public class NilaiMeanKelasDaoImpl extends BaseHibernate implements NilaiMeanKelasDao{

	@Override
	public List<Map<String, Object>> listMean() throws Exception {
		Query q = em.createNativeQuery("");
		return null;
	}

}
