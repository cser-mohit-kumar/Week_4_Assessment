package com.capgemini.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	boolean existsByEmail(String email);

}
