package com.example.demo.service.impliments;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.persistance.dao.*;
import com.example.demo.persistance.entities.*;
import com.example.demo.service.interfaces.*;
@Service
public class SpecialityService  implements  ISpeciality{
    @Autowired
    public SpecialityRepository specialityRepository;
    @Override
    public Speciality saveSpeciality(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    @Override
    public Speciality updateSpeciality(Speciality speciality) {
        return specialityRepository.saveAndFlush(speciality);
    }

    @Override
    public boolean deleteSpeciality(Long id) {
        specialityRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Speciality> getListSpeciality() {
        return specialityRepository.findAll();
    }

    @Override
    public Speciality findSpecialityByName(String name) {
        // TODO Auto-generated method stub
        return  specialityRepository.findBySpecialityName(name);
    }

    @Override
    public Speciality getSpeciality(Long id) {
        return specialityRepository.findById(id).get();
    }

    @Override
    public int getQuantityOfSpeciality() {

        return specialityRepository.getQuantityOfSpeciality();
    }

    @Override
    public Speciality getSpecialityById(Long id) {
        return null;
    }

    @Override
    public List<Speciality> getAll() {
        return  specialityRepository.findAll();
    }
}
