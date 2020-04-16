package com.lawencon.app.springbootproject.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime waktuMulai;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime waktuSelesai;

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

	public LocalDateTime getWaktuMulai() {
		return waktuMulai;
	}

	public void setWaktuMulai(LocalDateTime waktuMulai) {
		this.waktuMulai = waktuMulai;
	}

	public LocalDateTime getWaktuSelesai() {
		return waktuSelesai;
	}

	public void setWaktuSelesai(LocalDateTime waktuSelesai) {
		this.waktuSelesai = waktuSelesai;
	}

}
