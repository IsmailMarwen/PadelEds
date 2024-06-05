package com.example.demo.service.interfaces;

import java.util.List;

import com.example.demo.persistance.entities.Patient;

public interface IPatient {

		Patient savePatient(Patient patient);
	     Patient updatePatient(Patient patient);
	     boolean deletePatient(Long id);
	     List<Patient> getListPatient();
	     Patient getPatient(Long id);
	     Patient findPatientByName(String name);
	     int getQuantityOfPatient();
	     Patient getPatientByIdPatient(Long id);
		 List<Patient> getAllPatients();
}
