package com.lawencon.app.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Test {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid2")
	private String idTest;

	@OneToOne
	@JoinColumn(name = "idSubcourse")
	private Subcourse idSubcourse;

	@OneToOne
	@JoinColumn(name = "idSoal")
	private Soal fileSoal;

	@CreationTimestamp
	private java.sql.Timestamp waktuMulai;

	@CreationTimestamp
	private java.sql.Timestamp waktuSelesai;

	public String getIdTest() {
		return idTest;
	}

	public void setIdTest(String idTest) {
		this.idTest = idTest;
	}

	public Subcourse getIdSubcourse() {
		return idSubcourse;
	}

	public void setIdSubcourse(Subcourse idSubcourse) {
		this.idSubcourse = idSubcourse;
	}

	public Soal getFileSoal() {
		return fileSoal;
	}

	public void setFileSoal(Soal fileSoal) {
		this.fileSoal = fileSoal;
	}

	public java.sql.Timestamp getWaktuMulai() {
		return waktuMulai;
	}

	public void setWaktuMulai(java.sql.Timestamp waktuMulai) {
		this.waktuMulai = waktuMulai;
	}

	public java.sql.Timestamp getWaktuSelesai() {
		return waktuSelesai;
	}

	public void setWaktuSelesai(java.sql.Timestamp waktuSelesai) {
		this.waktuSelesai = waktuSelesai;
	}

}
