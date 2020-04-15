package com.lawencon.app.springbootproject.model;

import java.sql.Date;

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
	@JoinColumn(name = "idCourse")
	private Course idCourse;
	
	private Date tanggal;
	
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

	public Course getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Course idCourse) {
		this.idCourse = idCourse;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
