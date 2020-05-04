package com.lawencon.app.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nilai_mean_kelas")
public class NilaiMeanKelas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNilaiMean;
	
	private String idTest;
	
	private float nilaiMean;

	public int getIdNilaiMean() {
		return idNilaiMean;
	}

	public void setIdNilaiMean(int idNilaiMean) {
		this.idNilaiMean = idNilaiMean;
	}

	public String getIdTest() {
		return idTest;
	}

	public void setIdTest(String idTest) {
		this.idTest = idTest;
	}

	public float getNilaiMean() {
		return nilaiMean;
	}

	public void setNilaiMean(float nilaiMean) {
		this.nilaiMean = nilaiMean;
	}
}