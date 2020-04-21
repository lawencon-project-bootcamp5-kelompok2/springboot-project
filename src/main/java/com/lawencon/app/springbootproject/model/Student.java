package com.lawencon.app.springbootproject.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity @Table(name = "student")
public class Student {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idStudent;
	@Column(unique = true)
	private String npm;
	private String namaStudent;
	
	@ManyToMany
	@JoinColumn(name = "idKelas")
	private List<Kelas> kelas;
	
	private String role;

	public String getNpm() {
		return npm;
	}

	public void setNpm(String npm) {
		this.npm = npm;
	}

	public String getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(String idStudent) {
		this.idStudent = idStudent;
	}

	public String getNamaStudent() {
		return namaStudent;
	}

	public void setNamaStudent(String namaStudent) {
		this.namaStudent = namaStudent;
	}

	public List<Kelas> getKelas() {
		return kelas;
	}

	public void setKelas(List<Kelas> kelas) {
		this.kelas = kelas;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
