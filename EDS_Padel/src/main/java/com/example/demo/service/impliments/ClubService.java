package com.example.demo.service.impliments;
import java.security.SecureRandom;

import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.dao.AppWebRepository;
import com.example.demo.persistance.dao.ClubRepository;
import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.AppWeb;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.helper.ClubAppWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.interfaces.IClub;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class ClubService implements  IClub {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    public static String generateRandomPassword(int length) {
        if (length < 1) throw new IllegalArgumentException("Length must be greater than 0");

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
    @Autowired
    public ClubRepository clubRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppWebService appWebService;
    @Autowired
    private AdministrateurService administrateurService;

    @Autowired
    private AppWebRepository appWebRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Club saveClub(ClubAppWebRequest clubAppWebRequest) {
        if (clubRepository.existsByEmail(clubAppWebRequest.getClub().getEmail())) {
            return null;
        }

        if (clubRepository.existsByNomClub(clubAppWebRequest.getClub().getNomClub())) {
            return null;
        }
        System.out.println(appWebRepository.existsByAdresseUrl(clubAppWebRequest.getAppWeb().getAdresseUrl()));

        if (appWebRepository.existsByAdresseUrl(clubAppWebRequest.getAppWeb().getAdresseUrl())) {
            return null;
        }

        String generatedPassword = generateRandomPassword(8);
        Club savedClub = clubRepository.save(clubAppWebRequest.getClub());
        clubAppWebRequest.getAppWeb().setClub(savedClub);
        AppWeb savedAppWeb = appWebService.saveAppWeb(clubAppWebRequest.getAppWeb());
        savedClub.setAppWeb(savedAppWeb);
        savedClub.setActivites(clubAppWebRequest.getClub().getActivites());
        clubRepository.save(savedClub);
        Administrateur admin = new Administrateur();
        admin.setUsername("admin" + generateRandomPassword(4));
        admin.setPassword(generatedPassword);
        admin.setRole("admin");
        admin.setClub(savedClub);
        admin.setEmail(savedClub.getEmail());
        administrateurService.saveAdminstrateur(admin);
        return savedClub;
    }

    @Override
    public Club updateClub(Club Club) {
        return clubRepository.saveAndFlush(Club);
    }

    @Override
    public boolean deleteClub(Long id) {
        clubRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Club> getListClub() {
        return clubRepository.findAll();
    }

    @Override
    public Club getClubByIdClub(Long id) {
        return clubRepository.findById(id).get();
    }

    @Override
    public List<Club> getClubsNearLocation(double latitude, double longitude, double distance) {
        return clubRepository.findClubsNearLocation(latitude, longitude, distance);

    }

    @Override
    public List<Club> getClubsByNomClub(String nomClub) {
        return  clubRepository.findByNomClubContainingIgnoreCase(nomClub);
    }

    @Override
    public List<Club> getClubsByVille(String ville) {
        return clubRepository.findByVille(ville);
    }
    @Override
    public boolean emailExists(String email) {
        return clubRepository.existsByEmail(email);
    }
    @Override
    public boolean nomClubExists(String nomClub) {
        return clubRepository.existsByNomClub(nomClub);
    }
    @Override
    public boolean adresseUrlExists(String adresseUrl) {
        return appWebService.existsByAdresseUrl(adresseUrl);
    }
}

