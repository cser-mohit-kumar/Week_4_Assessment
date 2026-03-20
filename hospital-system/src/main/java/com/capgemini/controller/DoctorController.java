package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.dto.DoctorDTO;
import com.capgemini.model.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@PostMapping
	public ResponseEntity<Object> createDoctor(@RequestBody DoctorDTO dto) {
		return doctorService.createDoctor(dto);
	}

	@GetMapping
	public List<DoctorDTO> getAllDoctors() {
		return doctorService.getAllDoctors();
	}

	@GetMapping("/{doctorId}")
	public ResponseEntity<Object> getDoctorById(@PathVariable Integer doctorId) {
		return doctorService.getDoctorById(doctorId);
	}

	@PutMapping("/{doctorId}")
	public ResponseEntity<Object> updateDoctor(@PathVariable Integer doctorId, @RequestBody DoctorDTO dto) {
		return doctorService.updateDoctor(doctorId, dto);
	}

	@DeleteMapping("/{doctorId}")
	public ResponseEntity<Object> deleteDoctor(@PathVariable Integer doctorId) {
		return doctorService.deleteDoctor(doctorId);
	}

}
