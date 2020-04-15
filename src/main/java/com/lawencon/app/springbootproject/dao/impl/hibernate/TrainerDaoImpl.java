package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.TrainerDao;
import com.lawencon.app.springbootproject.model.Trainer;

@Repository
public class TrainerDaoImpl extends BaseHibernate implements TrainerDao {

	@Override
	public void createTrainer(Trainer trainer) {
		em.persist(trainer);
	}

	@Override
	public void updateTrainer(Trainer trainer) {
		Trainer t = findById(trainer);
		t.setEmailTrainer(trainer.getEmailTrainer());
		t.setPwdTrainer(trainer.getPwdTrainer());
		t.setRole(trainer.getRole());
		em.merge(t);
	}

	@Override
	public void deleteTrainer(Trainer trainer) {
		em.remove(findById(trainer));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trainer> findAll() {
		Query q = em.createQuery(" FROM Trainer");
		return q.getResultList();
	}

	@Override
	public Trainer findById(Trainer trainer) {
		Query q = em.createQuery(" FROM Trainer WHERE idTrainer =:idParam");
		q.setParameter("idParam", trainer.getIdTrainer());
		return (Trainer) q.getSingleResult();
	}

	
}
