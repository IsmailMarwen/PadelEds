package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.service.interfaces.IAdministrateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AdministrateurService implements IAdministrateur {
    @Autowired
    public AdminstarteurRepository adminstarteurRepository;
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


}
