package com.example.demo.persistance.dao;
import com.example.demo.persistance.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.persistance.entities.Consultation;
public interface ConsultationRepository extends JpaRepository<Consultation,Long> {



    @Query(value = "select count(*) from consultation",nativeQuery = true)
    int getQuantityOfConsultation();
    @Query(value = "select * from consultation where id_cons= :id",nativeQuery = true)
    Consultation getConsultationByIdConsultation(@Param("id") Long id);
}
