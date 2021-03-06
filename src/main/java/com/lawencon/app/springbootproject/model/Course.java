package com.lawencon.app.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity @Table(name = "course")
public class Course {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idCourse;
	
	@ManyToOne
	@JoinColumn(name = "idTrainer")
	private Trainer trainer;
	
	private String namaCourse;
	
	private String deskripsi;
	
	public String getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(String idCourse) {
		this.idCourse = idCourse;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setIdTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public String getNamaCourse() {
		return namaCourse;
	}

	public void setNamaCourse(String namaCourse) {
		this.namaCourse = namaCourse;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
}
