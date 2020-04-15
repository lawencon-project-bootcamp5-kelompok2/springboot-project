package com.lawencon.app.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class ForumAnswer {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idAnswer;

	@ManyToOne
	@JoinColumn(name = "idForum")
	private Forum idForum;

	private String emailAnswer;

	private String balasan;

	public String getIdAnswer() {
		return idAnswer;
	}

	public void setIdAnswer(String idAnswer) {
		this.idAnswer = idAnswer;
	}

	public Forum getIdForum() {
		return idForum;
	}

	public void setIdForum(Forum idForum) {
		this.idForum = idForum;
	}

	public String getEmailAnswer() {
		return emailAnswer;
	}

	public void setEmailAnswer(String emailAnswer) {
		this.emailAnswer = emailAnswer;
	}

	public String getBalasan() {
		return balasan;
	}

	public void setBalasan(String balasan) {
		this.balasan = balasan;
	}

}
