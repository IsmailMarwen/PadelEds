package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.TypeAbonnement;

import java.util.List;

public interface ITypeAbonnement {
    TypeAbonnement saveTypeAbonnement(TypeAbonnement typeAbonnement);
    TypeAbonnement updateTypeAbonnement(TypeAbonnement typeAbonnement);
    boolean deleteTypeAbonnement(Long id);
    List<TypeAbonnement> getListTypeAbonnement();
    TypeAbonnement getTypeAbonnementByIdTypeAbonnement(Long id);
}
