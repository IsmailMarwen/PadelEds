package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.MaterielRepository;
import com.example.demo.persistance.entities.Materiel;
import com.example.demo.service.interfaces.IMateriel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
    public class MaterielService implements IMateriel {
        @Autowired
        public MaterielRepository materielRepository;

    @Override
    public Materiel saveMateriel(Materiel materiel) {
        return materielRepository.save(materiel);
    }

    @Override
    public Materiel updateMateriel(Materiel materiel) {
        return materielRepository.saveAndFlush(materiel);
    }

    @Override
    public boolean deleteMateriel(Long id) {
        materielRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Materiel> getListMateriel() {
        return materielRepository.findAll();
    }

    @Override
    public Materiel getMaterielByIdMateriel(Long id) {
        return materielRepository.findById(id).get();
    }
}
