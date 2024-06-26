package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.DeviseRepository;
import com.example.demo.persistance.entities.Devise;
import com.example.demo.service.interfaces.IDevise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeviseService implements IDevise {
    @Autowired
    public DeviseRepository deviseRepository;
    @Override
    public Devise saveDevise(Devise devise) {
        return deviseRepository.save(devise);
    }

    @Override
    public Devise updateDevise(Devise devise) {

        return deviseRepository.saveAndFlush(devise);
    }
    @Override
    public boolean deleteDevise(Long id) {
        deviseRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Devise> getListDevise() {
        return deviseRepository.findAll();
    }

    @Override
    public Devise getDeviseByIdDevise(Long id) {
        return deviseRepository.findById(id).get();
    }
}
