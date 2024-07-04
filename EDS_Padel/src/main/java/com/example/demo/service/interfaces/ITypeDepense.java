package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Banque;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.TypeDepense;

import java.util.List;

public interface ITypeDepense {
    TypeDepense saveTypeDepense(TypeDepense typeDepense);
    TypeDepense updateTypeDepense(TypeDepense typeDepense);
    boolean deleteTypeDepense(Long id);
    List<TypeDepense> getListTypeDepense();
    TypeDepense getTypeDepenseByIdTypeDepense(Long id);
    List<TypeDepense> getListTypeDepenseByClub(Long idClub);

}
