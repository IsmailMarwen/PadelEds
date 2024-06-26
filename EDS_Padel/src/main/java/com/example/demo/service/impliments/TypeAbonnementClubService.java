package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.TypeAbonnementClubRepository;
import com.example.demo.persistance.entities.TypeAbonnementClub;
import com.example.demo.service.interfaces.ITypeAbonnementClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeAbonnementClubService implements ITypeAbonnementClub {
    @Autowired
    public TypeAbonnementClubRepository typeAbonnementClubRepository;
    @Override
    public TypeAbonnementClub saveTypeAbonnementClub(TypeAbonnementClub typeAbonnementClub) {
        return typeAbonnementClubRepository.save(typeAbonnementClub);
    }

    @Override
    public TypeAbonnementClub updateTypeAbonnementClub(TypeAbonnementClub typeAbonnementClub) {

        return typeAbonnementClubRepository.saveAndFlush(typeAbonnementClub);
    }
    @Override
    public boolean deleteTypeAbonnementClub(Long id) {
        typeAbonnementClubRepository.deleteById(id);
        return true;
    }

    @Override
    public List<TypeAbonnementClub> getListTypeAbonnementClub() {
        return typeAbonnementClubRepository.findAll();
    }

    @Override
    public TypeAbonnementClub getTypeAbonnementClubByIdTypeAbonnementClub(Long id) {
        return typeAbonnementClubRepository.findById(id).get();
    }
}
