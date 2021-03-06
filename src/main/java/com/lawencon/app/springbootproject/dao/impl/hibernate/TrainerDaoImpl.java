package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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
import com.lawencon.app.springbootproject.model.payload.request.SignupRequest;

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
		Trainer t = findById(trainer.getIdTrainer());	// ambil data trainer yg lama
		
		Role role = roleDao.findRoleTrainer();
		Set<Role> hash = new HashSet<>();
		hash.add(role);
		
		Login temp = new Login();				// buat nyimpen hasil update
		temp.setRoles(hash);					
		temp.setNama(trainer.getNamaTrainer());
		temp.setEmail(trainer.getEmail());
		temp.setHp(trainer.getHp());
		temp.setPassword(encoder.encode(trainer.getPassword()));
		em.persist(temp);
		
		loginDao.deleteByEmail(t.getEmail());	// hapus data login yg lama
		
		t.setNamaTrainer(trainer.getNamaTrainer());
		t.setEmail(trainer.getEmail());
		t.setPassword(encoder.encode(trainer.getPassword()));
		t.setHp(trainer.getHp());
		em.merge(t);				
		
	}

	@Override
	public void deleteTrainer(String idTrainer) throws Exception{
		Trainer t = findById(idTrainer);
		Login l = loginDao.findByEmail(t.getEmail());
		em.remove(l);
		em.remove(t);
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
	public List<?> cetakReportTrainer(String idKelas, String idSubcourse) throws Exception {
		Query q = em.createNativeQuery("select distinct s.nama_student, a.status, j.nilai, sc.nama_subcourse, tr.nama_trainer, k.kode_kelas" + 
				"   from subcourse sc join absensi a on sc.id_subcourse = a.id_subcourse " + 
				"	join student s on s.id_student = a.id_student " + 
				"	join jawaban j on j.id_student = s.id_student " + 
				"	join course c on c.id_course = sc.id_course " + 
				"	join kelas k on k.id_course = c.id_course " + 
				"	join student_kelas sk on sk.student_id_student = s.id_student " + 
				"	join pertemuan p on p.id_pertemuan = a.id_pertemuan " + 
				"	join test t on t.id_pertemuan = p.id_pertemuan " + 
				"	join trainer tr on tr.id_trainer = c.id_trainer " +
				"	where sk.kelas_id_kelas = :idKelas and sc.id_subcourse = :subParam and sk.kelas_id_kelas = k.id_kelas and t.id_test = j.id_test ")
				.setParameter("idKelas", idKelas).setParameter("subParam", idSubcourse);
		return bMapperHibernate(q.getResultList(), "namaStudent", "status", "nilai", "namaSubcourse", "namaTrainer", "kodeKelas");
	}

	@Override
	public String getNamaTrainer(String idTrainer) throws Exception {
		Query q = em.createNativeQuery("SELECT nama_trainer FROM trainer WHERE id_trainer = :idParam").setParameter("idParam", idTrainer);
		return (String) q.getSingleResult();
	}

	@Override
	public void createTrainers(SignupRequest signUpRequest) throws Exception {
		Login user = new Login(signUpRequest.getNama(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getHp());
		Role modRole = roleDao.findRoleTrainer();
		Set<Role> roles = new HashSet<>();
		roles.add(modRole);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		Random random = new Random();
		Trainer trainer = new Trainer();
		trainer.setEmail(user.getEmail());
		trainer.setNamaTrainer(user.getNama());
		trainer.setPassword(user.getPassword());
		trainer.setHp(user.getHp());
		trainer.setRole(modRole.getName().toString());
		trainer.setNik("02"+formatter.format(LocalDate.now()).toString()+random.nextInt(900));
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Trainer> findByNamaAndEmail(String search) throws Exception {
		Query q = em.createQuery("from Trainer where upper(email) like upper(concat('%',:searchParam,'%')) or upper(namaTrainer) like upper(concat('%',:searchParam,'%')) ");
		q.setParameter("searchParam", search);
		return q.getResultList();
	}

	@Override
	public Trainer findByEmail(String email) throws Exception {
		Query q = em.createQuery(" from Trainer where email = :emailParam");
		q.setParameter("emailParam", email);
		return (Trainer) q.getSingleResult();
	}
}
