package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.dao.CoachRepository;
import com.example.demo.persistance.dao.MembreRepository;
import com.example.demo.persistance.entities.*;
import com.example.demo.persistance.helper.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private AdminstarteurRepository administrateurRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String authenticateAndGenerateToken(String username, String password, Club club) {
        Membre membre = membreRepository.findByUsernameAndClub(username, club);
        if (membre != null && membre.getPassword().equals(password)) {
            return generateToken(membre);
        }


        Coach coach = coachRepository.findByUsernameAndClub(username, club);
        if (coach != null && coach.getPassword().equals(password)) {
            return generateToken(coach);
        }


        Administrateur administrateur = administrateurRepository.findByUsernameAndClub(username, club);
        if (administrateur != null && administrateur.getPassword().equals(password)) {
            return generateToken(administrateur);
        }

        return null;
    }

    private String generateToken(Utilisateur utilisateur) {
        return jwtUtil.generateToken(utilisateur.getUsername(), utilisateur.getRole(), utilisateur.getClub().getIdClub());
    }
}
