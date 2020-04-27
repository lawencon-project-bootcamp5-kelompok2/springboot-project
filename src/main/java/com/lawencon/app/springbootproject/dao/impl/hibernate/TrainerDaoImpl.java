package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.LoginDao;
import com.lawencon.app.springbootproject.dao.RoleDao;
import com.lawencon.app.springbootproject.dao.TrainerDao;
import com.lawencon.app.springbootproject.model.Login;
import com.lawencon.app.springbootproject.model.Role;
import com.lawencon.app.springbootproject.model.Trainer;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;

@Repository
public class TrainerDaoImpl extends BaseHibernate implements TrainerDao {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public void updateTrainer(Trainer trainer) throws Exception{
		Trainer t = findById(trainer.getIdTrainer());
		t.setNamaTrainer(trainer.getNamaTrainer());
		t.setEmail(trainer.getEmail());
		t.setPassword(trainer.getPassword());
		t.setRole(trainer.getRole());
		em.merge(t);
	}

	@Override
	public void deleteTrainer(String idTrainer) throws Exception{
		em.remove(findById(idTrainer));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trainer> findAll() throws Exception{
		Query q = em.createQuery(" FROM Trainer");
		return q.getResultList();
	}

	@Override
	public Trainer findById(String idTrainer) throws Exception{
		Query q = em.createQuery(" FROM Trainer WHERE idTrainer =:idParam");
		q.setParameter("idParam", idTrainer);
		return (Trainer) q.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> cetakReportTrainer(String idTrainer, String idTest, String idSubcourse) throws Exception {
		Query q = em.createNativeQuery("select distinct " + 
				"	s.nama_student, a.status, j.nilai, sc.nama_subcourse, t.nama_trainer \r\n" + 
				"from \r\n" + 
				"	student s \r\n" + 
				"	join absensi a on s.id_student = a.id_student " + 
				"	join subcourse sc on sc.id_subcourse = a.id_subcourse " +	
				"	join jawaban j on j.id_student = s.id_student \r\n" + 
				"	join course c on c.id_course = sc.id_course \r\n" + 
				"	join trainer t on t.id_trainer = c.id_trainer " + 
				"	where t.id_trainer = :idTrainer and j.id_test = :idTest and sc.id_subcourse = :idSubcourse ")
				.setParameter("idTrainer", idTrainer).setParameter("idTest", idTest).setParameter("idSubcourse", idSubcourse);
		return bMapperHibernate(q.getResultList(), "namaStudent", "status", "nilai", "namaSubcourse", "namaTrainer");
	}

	@Override
	public Trainer getNamaTrainer(String idTrainer) throws Exception {
		Query q = em.createNativeQuery("SELECT nama_trainer FROM trainer WHERE id_trainer = :idParam").setParameter("idParam", idTrainer);
		return (Trainer) q.getSingleResult();
	}

	@Override
	public void createTrainers(SignupRequest signUpRequest) throws Exception {
		Login user = new Login(signUpRequest.getNama(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getHp());
		Role modRole = roleDao.findRoleTrainer();
		Set<Role> roles = new HashSet<>();
		roles.add(modRole);
		Trainer trainer = new Trainer();
		trainer.setEmail(user.getEmail());
		trainer.setNamaTrainer(user.getNama());
		trainer.setPassword(user.getPassword());
		trainer.setHp(user.getHp());
		trainer.setRole(modRole.getName().toString());
		em.persist(trainer);
		user.setRoles(roles);
		loginDao.insertUser(user);
	}

	@Override
	public Trainer validTrainers(SignupRequest signUpRequest) throws Exception {
		Query q = em.createQuery("from Trainer where email = :emailParam and namaTrainer = :namaParam");
		q.setParameter("emailParam", signUpRequest.getEmail());
		q.setParameter("namaParam", signUpRequest.getNama());
		return (Trainer) q.getSingleResult();
	}
}
