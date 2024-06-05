package com.example.demo.persistance.dao;
import com.example.demo.persistance.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.persistance.entities.Medecin;
public interface MedecinRepository extends JpaRepository <Medecin,Long>{
    Medecin findByNom(String nom);


    @Query(value = "select count(*) from medecin",nativeQuery = true)
    int getQuantityOfMedecin();
    @Query(value = "select * from medecin where id= :id",nativeQuery = true)
    Medecin getMedecinByIdMedecin(@Param("id") Long id);
}
