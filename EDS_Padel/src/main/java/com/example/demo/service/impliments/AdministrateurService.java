package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.dao.ClubRepository;
import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.Club;
import com.example.demo.service.interfaces.IAdministrateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministrateurService implements IAdministrateur {
    @Autowired
    public AdminstarteurRepository adminstarteurRepository;
    @Autowired
    public ClubService clubService;
    @Override
    public Administrateur saveAdminstrateur(Administrateur administrateur) {
        return adminstarteurRepository.save(administrateur);
    }

    @Override
    public Administrateur updateAdminstarteur(Administrateur administrateur) {
        return adminstarteurRepository.saveAndFlush(administrateur);
    }

    @Override
    public boolean deleteAdminstarteur(Long id) {
        adminstarteurRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Administrateur> getListAdminstarteur() {
        return adminstarteurRepository.findAll();
    }

    @Override
    public Administrateur getAdminstarteurByIdAdminstarteur(Long id) {
        return adminstarteurRepository.findById(id).get();
    }

    @Override
    public List<Administrateur> getListAdminstarteurByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return  adminstarteurRepository.getAllByClub(club);
    }


}
