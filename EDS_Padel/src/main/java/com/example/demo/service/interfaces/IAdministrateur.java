package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Administrateur;

import java.util.List;

public interface IAdministrateur {
    Administrateur saveAdminstrateur(Administrateur administrateur);
    Administrateur updateAdminstarteur(Administrateur administrateur);
    boolean deleteAdminstarteur(Long id);
    List<Administrateur> getListAdminstarteur();
    Administrateur getAdminstarteurByIdAdminstarteur(Long id);
}
