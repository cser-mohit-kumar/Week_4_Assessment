package com.capgemini.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appointmentId;
	private String patientName;
	private LocalDateTime scheduledTime;
	private String status;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	public Appointment() {
		super();
	}

	public Appointment(Integer appointmentId, String patientName, LocalDateTime scheduledTime, String status, Doctor doctor) {
		super();
		this.appointmentId = appointmentId;
		this.patientName = patientName;
		this.scheduledTime = scheduledTime;
		this.status = status;
		this.doctor = doctor;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public LocalDateTime getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(LocalDateTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", patientName=" + patientName
				+ ", scheduledTime=" + scheduledTime + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(appointmentId, patientName, scheduledTime, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(appointmentId, other.appointmentId)
				&& Objects.equals(patientName, other.patientName)
				&& Objects.equals(scheduledTime, other.scheduledTime)
				&& Objects.equals(status, other.status);
	}

}
