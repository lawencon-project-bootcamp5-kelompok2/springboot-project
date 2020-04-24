package com.lawencon.app.springbootproject.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;


@Entity @Table(name = "student")
public class Student {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idStudent;
	
	@Column(unique = true)
	private String npm;
	
	@NotBlank
	@Size(max = 20)
	private String namaStudent;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	@ManyToMany
	@JoinColumn(name = "idKelas")
	private List<Kelas> kelas;
	
	private String role;
	
	public Student() {
	}

	public Student(String namaStudent, String email, String password) {
		this.namaStudent = namaStudent;
		this.email = email;
		this.password = password;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
