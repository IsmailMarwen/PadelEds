package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.BanqueRepository;
import com.example.demo.persistance.entities.Banque;
import com.example.demo.persistance.entities.Club;
import com.example.demo.service.interfaces.IBanque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BanqueService  implements IBanque {
    @Autowired
    public BanqueRepository banqueRepository;
    @Autowired
    public  ClubService clubService;
    @Override
    public Banque saveBanque(Banque banque) {
        return banqueRepository.save(banque);
    }

    @Override
    public Banque updateBanque(Banque banque) {

        return banqueRepository.saveAndFlush(banque);
    }
    @Override
    public boolean deleteBanque(Long id) {
        banqueRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Banque> getListBanque() {
        return banqueRepository.findAll();
    }

    @Override
    public Banque getBanqueByIdBanque(Long id) {
        return banqueRepository.findById(id).get();
    }

    @Override
    public List<Banque> getListBanqueByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return  banqueRepository.getAllByClub(club);
    }
}
