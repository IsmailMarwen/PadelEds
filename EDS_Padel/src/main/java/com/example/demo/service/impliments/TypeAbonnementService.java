package com.example.demo.service.impliments;
import com.example.demo.persistance.entities.TypeAbonnement;

import com.example.demo.persistance.dao.TypeAbonnementRepository;
import com.example.demo.service.interfaces.ITypeAbonnement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeAbonnementService implements ITypeAbonnement {

    @Autowired
    public TypeAbonnementRepository typeAbonnementRepository;

    @Override
    public TypeAbonnement saveTypeAbonnement(TypeAbonnement typeAbonnement) {
        return typeAbonnementRepository.save(typeAbonnement);
    }

    @Override
    public TypeAbonnement updateTypeAbonnement(TypeAbonnement TypeAbonnement) {
        return typeAbonnementRepository.saveAndFlush(TypeAbonnement);
    }

    @Override
    public boolean deleteTypeAbonnement(Long id) {
        typeAbonnementRepository.deleteById(id);
        return true;
    }

    @Override
    public List<TypeAbonnement> getListTypeAbonnement() {
        return typeAbonnementRepository.findAll();
    }

    @Override
    public TypeAbonnement getTypeAbonnementByIdTypeAbonnement(Long id) {
        return typeAbonnementRepository.findById(id).get();
    }
}
