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
		t.setNamaTrainer(trainer.getNamaTrainer());
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

	@SuppressWarnings("unchecked")
	@Override
	public List<?> cetakReportTrainer(String id) throws Exception {
		Query q = em.createNativeQuery("select \r\n" + 
				"	s.nama_student as namaStudent, a.status, j.nilai, sc.nama_subcourse \r\n" + 
				"from \r\n" + 
				"	student s \r\n" + 
				"	join absensi a on s.id_student = a.id_student \r\n" + 
				"	join jawaban j on j.id_student = s.id_student \r\n" + 
				"	join course c on c.id_course = s.id_course \r\n" + 
				"	join trainer t on t.id_trainer = c.id_trainer join subcourse sc on sc.id_course = c.id_course where t.id_trainer = :idParam").setParameter("idParam", id);
		return bMapperHibernate(q.getResultList(), "namaStudent", "status", "nilai", "namaSubcourse");
	}

	@Override
	public Trainer validTrainer(Trainer trainer) throws Exception {
		Query q = em.createQuery("from Trainer where emailTrainer = :emailParam and namaTrainer = :namaParam");
		q.setParameter("emailParam", trainer.getEmailTrainer());
		q.setParameter("namaParam", trainer.getNamaTrainer());
		return (Trainer) q.getSingleResult();
	}
	
}
