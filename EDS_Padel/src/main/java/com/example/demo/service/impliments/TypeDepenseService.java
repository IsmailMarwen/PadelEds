package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.TypeDepenseRepository;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.TypeDepense;
import com.example.demo.service.interfaces.ITypeDepense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TypeDepenseService implements ITypeDepense {
    @Autowired
    public TypeDepenseRepository typeDepenseRepository;
    @Autowired
    public ClubService clubService;
    @Override
    public TypeDepense saveTypeDepense(TypeDepense typeDepense) {
        return typeDepenseRepository.save(typeDepense);
    }

    @Override
    public TypeDepense updateTypeDepense(TypeDepense typeDepense) {

        return typeDepenseRepository.saveAndFlush(typeDepense);
    }
    @Override
    public boolean deleteTypeDepense(Long id) {
        typeDepenseRepository.deleteById(id);
        return true;
    }

    @Override
    public List<TypeDepense> getListTypeDepense() {
        return typeDepenseRepository.findAll();
    }

    @Override
    public TypeDepense getTypeDepenseByIdTypeDepense(Long id) {
        return typeDepenseRepository.findById(id).get();
    }

    @Override
    public List<TypeDepense> getListTypeDepenseByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return typeDepenseRepository.getAllByClub(club);
    }
}
