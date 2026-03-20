package com.capgemini.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.model.dto.AppointmentDTO;
import com.capgemini.model.entity.Appointment;
import com.capgemini.model.entity.Doctor;
import com.capgemini.model.repository.AppointmentRepository;
import com.capgemini.model.repository.DoctorRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	public ResponseEntity<Object> createAppointment(Integer doctorId, AppointmentDTO dto) {
		Optional<Doctor> optional = doctorRepository.findById(doctorId);
		if (optional.isEmpty())
			return doctorNotFound(doctorId);

		Appointment appointment = new Appointment();
		appointment.setPatientName(dto.getPatientName());
		appointment.setScheduledTime(dto.getScheduledTime());
		appointment.setStatus(dto.getStatus());
		appointment.setDoctor(optional.get());
		appointmentRepository.save(appointment);
		return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(appointment));
	}

	public ResponseEntity<Object> getAppointments(Integer doctorId, int page, int size) {
		Optional<Doctor> optional = doctorRepository.findById(doctorId);
		if (optional.isEmpty())
			return doctorNotFound(doctorId);

		Pageable pageable = PageRequest.of(page, size, Sort.by("scheduledTime").ascending());
		Page<Appointment> apptPage = appointmentRepository.findByDoctor(optional.get(), pageable);

		Map<String, Object> response = new HashMap<>();
		List<AppointmentDTO> content = new ArrayList<>();
		for (Appointment a : apptPage.getContent())
			content.add(toDTO(a));

		response.put("content", content);
		response.put("pageNumber", apptPage.getNumber());
		response.put("pageSize", apptPage.getSize());
		response.put("totalElements", apptPage.getTotalElements());
		response.put("totalPages", apptPage.getTotalPages());
		response.put("last", apptPage.isLast());
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<Object> getAppointmentById(Integer doctorId, Integer apptId) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (doctor.isEmpty())
			return doctorNotFound(doctorId);

		Optional<Appointment> optional = appointmentRepository.findById(apptId);
		if (optional.isEmpty())
			return apptNotFound(apptId);

		return ResponseEntity.ok(toDTO(optional.get()));
	}

	public ResponseEntity<Object> updateAppointment(Integer doctorId, Integer apptId, AppointmentDTO dto) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (doctor.isEmpty())
			return doctorNotFound(doctorId);

		Optional<Appointment> optional = appointmentRepository.findById(apptId);
		if (optional.isEmpty())
			return apptNotFound(apptId);

		Appointment appointment = optional.get();
		appointment.setPatientName(dto.getPatientName());
		appointment.setScheduledTime(dto.getScheduledTime());
		appointment.setStatus(dto.getStatus());
		appointmentRepository.save(appointment);
		return ResponseEntity.ok(toDTO(appointment));
	}

	public ResponseEntity<Object> deleteAppointment(Integer doctorId, Integer apptId) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorId);
		if (doctor.isEmpty())
			return doctorNotFound(doctorId);

		Optional<Appointment> optional = appointmentRepository.findById(apptId);
		if (optional.isEmpty())
			return apptNotFound(apptId);

		appointmentRepository.deleteById(apptId);
		Map<String, String> response = new HashMap<>();
		response.put("message", "Appointment deleted successfully");
		return ResponseEntity.ok(response);
	}

	public List<AppointmentDTO> getPendingAppointments() {
		List<AppointmentDTO> list = new ArrayList<>();
		for (Appointment a : appointmentRepository.findByStatusOrderByScheduledTimeAsc("PENDING"))
			list.add(toDTO(a));
		return list;
	}

	private AppointmentDTO toDTO(Appointment appointment) {
		AppointmentDTO dto = new AppointmentDTO();
		dto.setAppointmentId(appointment.getAppointmentId());
		dto.setPatientName(appointment.getPatientName());
		dto.setScheduledTime(appointment.getScheduledTime());
		dto.setStatus(appointment.getStatus());
		return dto;
	}

	private ResponseEntity<Object> doctorNotFound(Integer id) {
		Map<String, Object> error = new HashMap<>();
		error.put("status", 404);
		error.put("error", "Not Found");
		error.put("message", "Doctor with ID " + id + " not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	private ResponseEntity<Object> apptNotFound(Integer id) {
		Map<String, Object> error = new HashMap<>();
		error.put("status", 404);
		error.put("error", "Not Found");
		error.put("message", "Appointment with ID " + id + " not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
