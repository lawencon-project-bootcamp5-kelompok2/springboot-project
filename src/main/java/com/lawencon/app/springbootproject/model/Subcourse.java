package com.lawencon.app.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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

//	@Temporal(TemporalType.DATE)
	private java.sql.Date tanggalMulai;

//	@Temporal(TemporalType.DATE)
	private java.sql.Date tanggalSelesai;

	@OneToOne
	@JoinColumn(name = "idMateri")
	private Materi materi;

	@OneToOne
	@JoinColumn(name = "idForum")
	private Forum idForum;

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

	public java.sql.Date getTanggalMulai() {
		return tanggalMulai;
	}

	public void setTanggalMulai(java.sql.Date tanggalMulai) {
		this.tanggalMulai = tanggalMulai;
	}

	public java.sql.Date getTanggalSelesai() {
		return tanggalSelesai;
	}

	public void setTanggalSelesai(java.sql.Date tanggalSelesai) {
		this.tanggalSelesai = tanggalSelesai;
	}

	public Materi getMateri() {
		return materi;
	}

	public void setMateri(Materi materi) {
		this.materi = materi;
	}

	public Forum getIdForum() {
		return idForum;
	}

	public void setIdForum(Forum idForum) {
		this.idForum = idForum;
	}

}
