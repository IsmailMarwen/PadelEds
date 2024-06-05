package com.example.demo.persistance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.persistance.entities.CategorieAbonnement;
public interface CategorieAbonnementRepository extends JpaRepository<CategorieAbonnement,Long> {




    @Query(value = "select count(*) from CategorieAbonnement",nativeQuery = true)
    int getQuantityOfCategorieAbonnement();
    @Query(value = "select * from CategorieAbonnement where id= :idCategorie",nativeQuery = true)
    Patient getPatientByIdCategorieAbonnement(@Param("id") int idCategorie);

}