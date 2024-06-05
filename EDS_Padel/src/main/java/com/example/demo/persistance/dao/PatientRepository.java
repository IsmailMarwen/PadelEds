package com.example.demo.persistance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.persistance.entities.Patient;
public interface PatientRepository extends JpaRepository<Patient,Long> {

	Patient findByNom(String nomPat);
	

    @Query(value = "select count(*) from patient",nativeQuery = true)
    int getQuantityOfPatient();
    @Query(value = "select * from patient where id= :id",nativeQuery = true)
    Patient getPatientByIdPatient(@Param("id") Long id);

}
