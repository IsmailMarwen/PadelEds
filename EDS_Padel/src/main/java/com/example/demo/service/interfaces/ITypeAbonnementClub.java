package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.TypeAbonnementClub;

import java.util.List;

public interface ITypeAbonnementClub {
    TypeAbonnementClub saveTypeAbonnementClub(TypeAbonnementClub typeAbonnementClub);
    TypeAbonnementClub updateTypeAbonnementClub(TypeAbonnementClub typeAbonnementClub);
    boolean deleteTypeAbonnementClub(Long id);
    List<TypeAbonnementClub> getListTypeAbonnementClub();
    TypeAbonnementClub getTypeAbonnementClubByIdTypeAbonnementClub(Long id);
}
