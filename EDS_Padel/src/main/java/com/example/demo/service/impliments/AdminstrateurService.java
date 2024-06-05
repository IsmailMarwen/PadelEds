package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.entities.Adminstrateur;
import com.example.demo.service.interfaces.IAdminstrateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AdminstrateurService implements IAdminstrateur {
    @Autowired
    public AdminstarteurRepository adminstarteurRepository;
    @Override
    public Adminstrateur saveAdminstrateur(Adminstrateur adminstrateur) {
        return adminstarteurRepository.save(adminstrateur);
    }

    @Override
    public Adminstrateur updateAdminstarteur(Adminstrateur adminstrateur) {
        return adminstarteurRepository.saveAndFlush(adminstrateur);
    }

    @Override
    public boolean deleteAdminstarteur(Long id) {
        adminstarteurRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Adminstrateur> getListAdminstarteur() {
        return adminstarteurRepository.findAll();
    }

    @Override
    public Adminstrateur getAdminstarteurByIdAdminstarteur(Long id) {
        return adminstarteurRepository.findById(id).get();
    }


}
