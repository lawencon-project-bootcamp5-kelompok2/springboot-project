package com.lawencon.app.springbootproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "kelas")
public class Kelas {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idKelas;
	@Column(unique = true)
	private String kodeKelas;
	
	@Temporal(TemporalType.TIME)
	private Date openKelas;
	
	private String deskripsi;
	
	@ManyToOne
	@JoinColumn(name = "idCourse")
	private Course course;

	public String getIdKelas() {
		return idKelas;
	}

	public void setIdKelas(String idKelas) {
		this.idKelas = idKelas;
	}

	public String getKodeKelas() {
		return kodeKelas;
	}

	public void setKodeKelas(String kodeKelas) {
		this.kodeKelas = kodeKelas;
	}

	public Date getOpenKelas() {
		return openKelas;
	}

	public void setOpenKelas(Date openKelas) {
		this.openKelas = openKelas;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
