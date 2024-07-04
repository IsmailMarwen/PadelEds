package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Activite;
import com.example.demo.persistance.entities.Banque;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Devise;

import java.util.List;

public interface IDevise {
    Devise saveDevise(Devise devise);
    Devise updateDevise(Devise devise);
    boolean deleteDevise(Long id);
    List<Devise> getListDevise();
    Devise getDeviseByIdDevise(Long id);
    List<Devise> getListDeviseByClub(Long idClub);

}
