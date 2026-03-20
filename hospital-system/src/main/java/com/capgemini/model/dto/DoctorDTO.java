package com.capgemini.model.dto;

public class DoctorDTO {

	private Integer doctorId;
	private String name;
	private String specialization;
	private String email;
	private String phone;
	private int totalAppointments;

	public DoctorDTO() {
		super();
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getTotalAppointments() {
		return totalAppointments;
	}

	public void setTotalAppointments(int totalAppointments) {
		this.totalAppointments = totalAppointments;
	}

}
