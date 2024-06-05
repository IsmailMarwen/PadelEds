package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Speciality;

import java.util.List;

public interface ISpeciality {
    Speciality saveSpeciality(Speciality speciality);
    Speciality updateSpeciality(Speciality speciality);
    boolean deleteSpeciality(Long id);
    List<Speciality> getListSpeciality();

    Speciality findSpecialityByName(String name);

    Speciality getSpeciality(Long id);

    int getQuantityOfSpeciality();

    Speciality getSpecialityById(Long id);
    List<Speciality> getAll();
}
