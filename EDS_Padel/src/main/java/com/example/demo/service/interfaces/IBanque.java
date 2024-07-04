package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.Banque;
import com.example.demo.persistance.entities.Club;

import java.util.List;

public interface IBanque {
        Banque saveBanque(Banque banque);
        Banque updateBanque(Banque banque);
        boolean deleteBanque(Long id);
        List<Banque> getListBanque();
        Banque getBanqueByIdBanque(Long id);
        List<Banque> getListBanqueByClub(Long idClub);

}
