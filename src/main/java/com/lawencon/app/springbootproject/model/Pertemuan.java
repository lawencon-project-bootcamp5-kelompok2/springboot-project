package com.lawencon.app.springbootproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pertemuan")
public class Pertemuan {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idPertemuan;
	
	private String pertemuan;
	
	@Temporal(TemporalType.DATE)
	private Date tanggalPertemuan;
	
	@OneToOne
	@JoinColumn(name = "idSubcourse")
	private Subcourse idSubcourse;
	
	@OneToOne
	@JoinColumn(name = "idMateri")
	private Materi idMateri;
	
	public String getIdPertemuan() {
		return idPertemuan;
	}
	public void setIdPertemuan(String idPertemuan) {
		this.idPertemuan = idPertemuan;
	}
	public String getPertemuan() {
		return pertemuan;
	}
	public void setPertemuan(String pertemuan) {
		this.pertemuan = pertemuan;
	}
	public Date getTanggalPertemuan() {
		return tanggalPertemuan;
	}
	public void setTanggalPertemuan(Date tanggalPertemuan) {
		this.tanggalPertemuan = tanggalPertemuan;
	}
	public Subcourse getIdSubcourse() {
		return idSubcourse;
	}
	public void setIdSubcourse(Subcourse idSubcourse) {
		this.idSubcourse = idSubcourse;
	}
	public Materi getIdMateri() {
		return idMateri;
	}
	public void setIdMateri(Materi idMateri) {
		this.idMateri = idMateri;
	}
	
}
