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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.dto.AppointmentDTO;
import com.capgemini.model.service.AppointmentService;

@RestController
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/api/doctors/{doctorId}/appointments")
	public ResponseEntity<Object> createAppointment(@PathVariable Integer doctorId,
			@RequestBody AppointmentDTO dto) {
		return appointmentService.createAppointment(doctorId, dto);
	}

	@GetMapping("/api/doctors/{doctorId}/appointments")
	public ResponseEntity<Object> getAppointments(@PathVariable Integer doctorId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return appointmentService.getAppointments(doctorId, page, size);
	}

	@GetMapping("/api/doctors/{doctorId}/appointments/{apptId}")
	public ResponseEntity<Object> getAppointmentById(@PathVariable Integer doctorId,
			@PathVariable Integer apptId) {
		return appointmentService.getAppointmentById(doctorId, apptId);
	}

	@PutMapping("/api/doctors/{doctorId}/appointments/{apptId}")
	public ResponseEntity<Object> updateAppointment(@PathVariable Integer doctorId,
			@PathVariable Integer apptId,
			@RequestBody AppointmentDTO dto) {
		return appointmentService.updateAppointment(doctorId, apptId, dto);
	}

	@DeleteMapping("/api/doctors/{doctorId}/appointments/{apptId}")
	public ResponseEntity<Object> deleteAppointment(@PathVariable Integer doctorId,
			@PathVariable Integer apptId) {
		return appointmentService.deleteAppointment(doctorId, apptId);
	}

	@GetMapping("/api/appointments/pending")
	public List<AppointmentDTO> getPendingAppointments() {
		return appointmentService.getPendingAppointments();
	}

}
