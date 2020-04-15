package com.lawencon.app.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Forum {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idForum;

	private String emailSender;
	private String subjek;
	private String deskripsi;

	public String getIdForum() {
		return idForum;
	}

	public void setIdForum(String idForum) {
		this.idForum = idForum;
	}

	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public String getSubjek() {
		return subjek;
	}

	public void setSubjek(String subjek) {
		this.subjek = subjek;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

}
