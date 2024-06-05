package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Adminstrateur;

import java.util.List;

public interface IAdminstrateur {
    Adminstrateur saveAdminstrateur(Adminstrateur adminstrateur);
    Adminstrateur updateAdminstarteur(Adminstrateur adminstrateur);
    boolean deleteAdminstarteur(Long id);
    List<Adminstrateur> getListAdminstarteur();
    Adminstrateur getAdminstarteurByIdAdminstarteur(Long id);
}
