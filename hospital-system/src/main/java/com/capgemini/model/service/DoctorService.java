package com.capgemini.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.model.dto.DoctorDTO;
import com.capgemini.model.entity.Doctor;
import com.capgemini.model.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	public ResponseEntity<Object> createDoctor(DoctorDTO dto) {
		if (doctorRepository.existsByEmail(dto.getEmail())) {
			Map<String, Object> error = new HashMap<>();
			error.put("status", 409);
			error.put("error", "Conflict");
			error.put("message", "Email already registered");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		}
		Doctor doctor = new Doctor();
		doctor.setName(dto.getName());
		doctor.setSpecialization(dto.getSpecialization());
		doctor.setEmail(dto.getEmail());
		doctor.setPhone(dto.getPhone());
		doctorRepository.save(doctor);
		return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(doctor));
	}

	public ResponseEntity<Object> getDoctorById(Integer id) {
		Optional<Doctor> optional = doctorRepository.findById(id);
		if (optional.isEmpty())
			return notFound(id);
		return ResponseEntity.ok(toDTO(optional.get()));
	}

	public List<DoctorDTO> getAllDoctors() {
		List<DoctorDTO> list = new ArrayList<>();
		for (Doctor d : doctorRepository.findAll())
			list.add(toDTO(d));
		return list;
	}

	public ResponseEntity<Object> updateDoctor(Integer id, DoctorDTO dto) {
		Optional<Doctor> optional = doctorRepository.findById(id);
		if (optional.isEmpty())
			return notFound(id);
		Doctor doctor = optional.get();
		doctor.setName(dto.getName());
		doctor.setSpecialization(dto.getSpecialization());
		doctor.setEmail(dto.getEmail());
		doctor.setPhone(dto.getPhone());
		doctorRepository.save(doctor);
		return ResponseEntity.ok(toDTO(doctor));
	}

	public ResponseEntity<Object> deleteDoctor(Integer id) {
		Optional<Doctor> optional = doctorRepository.findById(id);
		if (optional.isEmpty())
			return notFound(id);
		doctorRepository.deleteById(id);
		Map<String, String> response = new HashMap<>();
		response.put("message", "Doctor deleted successfully");
		return ResponseEntity.ok(response);
	}

	private DoctorDTO toDTO(Doctor doctor) {
		DoctorDTO dto = new DoctorDTO();
		dto.setDoctorId(doctor.getDoctorId());
		dto.setName(doctor.getName());
		dto.setSpecialization(doctor.getSpecialization());
		dto.setEmail(doctor.getEmail());
		dto.setPhone(doctor.getPhone());
		dto.setTotalAppointments(doctor.getAppointments().size());
		return dto;
	}

	private ResponseEntity<Object> notFound(Integer id) {
		Map<String, Object> error = new HashMap<>();
		error.put("status", 404);
		error.put("error", "Not Found");
		error.put("message", "Doctor with ID " + id + " not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
