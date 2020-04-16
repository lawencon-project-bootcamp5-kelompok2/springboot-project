package com.lawencon.app.springbootproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date waktuMulai;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date waktuSelesai;

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

	public Date getWaktuMulai() {
		return waktuMulai;
	}

	public void setWaktuMulai(Date waktuMulai) {
		this.waktuMulai = waktuMulai;
	}

	public Date getWaktuSelesai() {
		return waktuSelesai;
	}

	public void setWaktuSelesai(Date waktuSelesai) {
		this.waktuSelesai = waktuSelesai;
	}

}
