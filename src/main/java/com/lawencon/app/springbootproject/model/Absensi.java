package com.lawencon.app.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Absensi {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idAbsensi;
	
	@OneToOne
	@JoinColumn(name = "idStudent")
	private Student idStudent;
	
	@OneToOne
	@JoinColumn(name = "idSubcourse")
	private Subcourse idSubcourse;
	
	@OneToOne
	@JoinColumn(name = "idPertemuan")
	private Pertemuan pertemuan;
	
	private String tanggal;
	
	private String status;

	public String getIdAbsensi() {
		return idAbsensi;
	}

	public void setIdAbsensi(String idAbsensi) {
		this.idAbsensi = idAbsensi;
	}

	public Student getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Student idStudent) {
		this.idStudent = idStudent;
	}

	public Subcourse getIdSubcourse() {
		return idSubcourse;
	}

	public void setIdSubcourse(Subcourse idSubcourse) {
		this.idSubcourse = idSubcourse;
	}

	public Pertemuan getPertemuan() {
		return pertemuan;
	}

	public void setPertemuan(Pertemuan pertemuan) {
		this.pertemuan = pertemuan;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
