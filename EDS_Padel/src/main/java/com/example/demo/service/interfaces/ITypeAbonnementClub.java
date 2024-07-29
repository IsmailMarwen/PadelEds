package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Banque;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.TypeAbonnementClub;

import java.util.List;

public interface ITypeAbonnementClub {
    TypeAbonnementClub saveTypeAbonnementClub(TypeAbonnementClub typeAbonnementClub);
    TypeAbonnementClub updateTypeAbonnementClub(TypeAbonnementClub typeAbonnementClub);
    boolean deleteTypeAbonnementClub(Long id);
    List<TypeAbonnementClub> getListTypeAbonnementClub();
    TypeAbonnementClub getTypeAbonnementClubByIdTypeAbonnementClub(Long id);
    List<TypeAbonnementClub> getListTypeAbonnementClubByClub(Long idClub);
List<TypeAbonnementClub> getListTypeAbonnementClubByClubAndCoach(Long idClub,Long idCoach);
}
