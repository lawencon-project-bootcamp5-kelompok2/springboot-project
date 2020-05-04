package com.lawencon.app.springbootproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Subcourse {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idSubcourse;

	@ManyToOne
	@JoinColumn(name = "idCourse")
	private Course idCourse;

	private String namaSubcourse;

	@Temporal(TemporalType.DATE)
	private Date tanggalMulai;

	@Temporal(TemporalType.DATE)
	private Date tanggalSelesai;
	
	private String deskripsi;

	public String getIdSubcourse() {
		return idSubcourse;
	}

	public void setIdSubcourse(String idSubcourse) {
		this.idSubcourse = idSubcourse;
	}

	public Course getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Course idCourse) {
		this.idCourse = idCourse;
	}

	public String getNamaSubcourse() {
		return namaSubcourse;
	}

	public void setNamaSubcourse(String namaSubcourse) {
		this.namaSubcourse = namaSubcourse;
	}

	public Date getTanggalMulai() {
		return tanggalMulai;
	}

	public void setTanggalMulai(Date tanggalMulai) {
		this.tanggalMulai = tanggalMulai;
	}

	public Date getTanggalSelesai() {
		return tanggalSelesai;
	}

	public void setTanggalSelesai(Date tanggalSelesai) {
		this.tanggalSelesai = tanggalSelesai;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

}
