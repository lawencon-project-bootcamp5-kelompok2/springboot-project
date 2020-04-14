package com.lawencon.app.springbootproject.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.model.Materi;

@Repository
public interface MateriRepo extends JpaRepository<Materi, String>{

}
