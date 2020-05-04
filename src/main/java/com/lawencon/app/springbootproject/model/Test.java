package com.lawencon.app.springbootproject.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	@JoinColumn(name = "idPertemuan")
	private Pertemuan idPertemuan;

	@OneToOne
	@JoinColumn(name = "idSoal")
	private Soal fileSoal;

	private Time waktuMulai;

	private Time waktuSelesai;

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

	public Pertemuan getIdPertemuan() {
		return idPertemuan;
	}

	public void setIdPertemuan(Pertemuan idPertemuan) {
		this.idPertemuan = idPertemuan;
	}

	public Soal getFileSoal() {
		return fileSoal;
	}

	public void setFileSoal(Soal fileSoal) {
		this.fileSoal = fileSoal;
	}

	public Time getWaktuMulai() {
		return waktuMulai;
	}

	public void setWaktuMulai(Time waktuMulai) {
		this.waktuMulai = waktuMulai;
	}

	public Time getWaktuSelesai() {
		return waktuSelesai;
	}

	public void setWaktuSelesai(Time waktuSelesai) {
		this.waktuSelesai = waktuSelesai;
	}
}
