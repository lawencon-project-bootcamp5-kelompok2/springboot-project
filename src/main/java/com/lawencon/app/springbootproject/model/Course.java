package com.lawencon.app.springbootproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Temporal(TemporalType.DATE)
	private Date waktuMulai;

	@Temporal(TemporalType.DATE)
	private Date waktuSelesai;
	
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

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}	
}
