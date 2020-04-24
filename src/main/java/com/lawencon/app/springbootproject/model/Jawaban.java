package com.lawencon.app.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Jawaban {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idJawaban;
	
	@OneToOne
	@JoinColumn(name = "idTest")
	private Test idTest;
	
	@OneToOne
	@JoinColumn(name = "idStudent")
	private Student idStudent;
	
	@OneToOne
	@JoinColumn(name = "idFileJawaban")
	private FileJawaban fileJawaban;
	
	private float nilai;

	public String getIdJawaban() {
		return idJawaban;
	}

	public void setIdJawaban(String idJawaban) {
		this.idJawaban = idJawaban;
	}

	public Test getIdTest() {
		return idTest;
	}

	public void setIdTest(Test idTest) {
		this.idTest = idTest;
	}

	public Student getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Student idStudent) {
		this.idStudent = idStudent;
	}

	public FileJawaban getFileJawaban() {
		return fileJawaban;
	}

	public void setFileJawaban(FileJawaban fileJawaban) {
		this.fileJawaban = fileJawaban;
	}

	public float getNilai() {
		return nilai;
	}

	public void setNilai(float nilai) {
		this.nilai = nilai;
	}
}
