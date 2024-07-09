package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Coach;
import com.example.demo.persistance.entities.Membre;

import java.util.List;

public interface IAdministrateur {
    Administrateur saveAdminstrateur(Administrateur administrateur);
    Administrateur updateAdminstarteur(Administrateur administrateur);
    boolean deleteAdminstarteur(Long id);
    List<Administrateur> getListAdminstarteur();
    Administrateur getAdminstarteurByIdAdminstarteur(Long id);
    List<Administrateur> getListAdminstarteurByClub(Long idClub);
    Coach ValidateCompteCoach(Coach c);
    Membre ValidateCompteMembre(Membre m);
}
