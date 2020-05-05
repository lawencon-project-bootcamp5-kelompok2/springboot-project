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
	private String idCourse;
	private String idTest;
	private String namaSubcourse;
	
	private float nilaiMean;

	public int getIdNilaiMean() {
		return idNilaiMean;
	}

	public void setIdNilaiMean(int idNilaiMean) {
		this.idNilaiMean = idNilaiMean;
	}

	public String getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(String idCourse) {
		this.idCourse = idCourse;
	}

	public String getIdTest() {
		return idTest;
	}

	public void setIdTest(String idTest) {
		this.idTest = idTest;
	}

	public String getNamaSubcourse() {
		return namaSubcourse;
	}

	public void setNamaSubcourse(String namaSubcourse) {
		this.namaSubcourse = namaSubcourse;
	}

	public float getNilaiMean() {
		return nilaiMean;
	}

	public void setNilaiMean(float nilaiMean) {
		this.nilaiMean = nilaiMean;
	}
}
