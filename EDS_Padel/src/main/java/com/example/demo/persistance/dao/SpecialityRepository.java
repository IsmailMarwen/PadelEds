package com.example.demo.persistance.dao;
import com.example.demo.persistance.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.persistance.entities.Speciality;
public interface SpecialityRepository extends JpaRepository<Speciality,Long> {
    Speciality findBySpecialityName(String specialityName);


    @Query(value = "select count(*) from speciality",nativeQuery = true)
    int getQuantityOfSpeciality();
    @Query(value = "select * from speciality where id= :id",nativeQuery = true)
    Speciality getSpecialityByIdSpeciality(@Param("id") Long id);

}
