package com.lawencon.app.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "kelas")
public class Kelas {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idKelas;
	private String kodeKelas;
	
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
