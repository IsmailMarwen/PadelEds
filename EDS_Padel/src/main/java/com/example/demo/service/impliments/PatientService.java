package com.example.demo.service.impliments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.persistance.dao.PatientRepository;
import com.example.demo.persistance.entities.Patient;
import com.example.demo.service.interfaces.IPatient;

@Service
public class PatientService implements IPatient {

	@Autowired
	PatientRepository patientrepository;
	
	@Override
	public Patient savePatient(Patient patient) {
		// TODO Auto-generated method stub
		return patientrepository.save(patient);
	}

	@Override
	public Patient updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		return patientrepository.saveAndFlush(patient);
	}

	@Override
	public boolean deletePatient(Long id) {
		// TODO Auto-generated method stub
		 patientrepository.deleteById(id);
		return true;
	}

	@Override
	public List<Patient> getListPatient() {
		// TODO Auto-generated method stub
		return  patientrepository.findAll();
	}

	@Override
	public Patient getPatient(Long id) {
		// TODO Auto-generated method stub
		return  patientrepository.findById(id).get();
	}

	@Override
	public Patient findPatientByName(String name) {
		// TODO Auto-generated method stub
		return  patientrepository.findByNom(name);
	}

	@Override
	public int getQuantityOfPatient() {
		// TODO Auto-generated method stub
		return  patientrepository.getQuantityOfPatient();
	}

	@Override
	public Patient getPatientByIdPatient(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientrepository.findAll();
	}

}
