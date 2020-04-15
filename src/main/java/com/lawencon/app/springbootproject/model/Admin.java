package com.lawencon.app.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idAdmin;

	private String emailAdmin;
	private String pwdAdmin;

	public String getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(String idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getEmailAdmin() {
		return emailAdmin;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

	public String getPwdAdmin() {
		return pwdAdmin;
	}

	public void setPwdAdmin(String pwdAdmin) {
		this.pwdAdmin = pwdAdmin;
	}

}
