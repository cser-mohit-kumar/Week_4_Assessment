package com.capgemini.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.entity.Appointment;
import com.capgemini.model.entity.Doctor;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	Page<Appointment> findByDoctor(Doctor doctor, Pageable pageable);

	List<Appointment> findByStatusOrderByScheduledTimeAsc(String status);

}
