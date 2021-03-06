package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.ForumAnswerDao;
import com.lawencon.app.springbootproject.model.ForumAnswer;

@Repository
public class ForumAnswerDaoImpl extends BaseHibernate implements ForumAnswerDao{

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from ForumAnswer");
		return q.getResultList();
	}

	@Override
	public ForumAnswer findById(String idAnswer) throws Exception {
		Query q = em.createQuery("from Forum where idAnswer = :idParam");
		q.setParameter("idParam", idAnswer);
		return (ForumAnswer) q.getSingleResult();
	}

	@Override
	public ForumAnswer update(ForumAnswer forumAnswer) throws Exception {
		ForumAnswer answer = findById(forumAnswer.getIdAnswer());
		answer.setEmailAnswer(forumAnswer.getEmailAnswer());
		em.merge(answer);
		return answer;
	}

	@Override
	public void createForumAnswer(ForumAnswer forumAnswer) throws Exception {
		em.persist(forumAnswer);
	}

	@Override
	public void delete(String idAnswer) throws Exception {
		em.remove(findById(idAnswer));
	}

}
