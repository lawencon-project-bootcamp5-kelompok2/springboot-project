package com.lawencon.app.springbootproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "trainer")
public class Trainer {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idTrainer;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	private String role;
	private String namaTrainer;
	private String hp;
	private String nik;

	public String getNamaTrainer() {
		return namaTrainer;
	}

	public void setNamaTrainer(String namaTrainer) {
		this.namaTrainer = namaTrainer;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getIdTrainer() {
		return idTrainer;
	}

	public void setIdTrainer(String idTrainer) {
		this.idTrainer = idTrainer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emailTrainer) {
		this.email = emailTrainer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pwdTrainer) {
		this.password = pwdTrainer;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

}
