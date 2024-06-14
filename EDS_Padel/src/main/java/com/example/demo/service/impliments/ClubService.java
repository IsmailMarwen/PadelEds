package com.example.demo.service.impliments;
import java.security.SecureRandom;
import com.example.demo.persistance.dao.ClubRepository;
import com.example.demo.persistance.entities.Club;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.interfaces.IClub;
import java.util.List;
import org.springframework.stereotype.Service;
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


    @Override
    public Club saveClub(Club club) {
        String generatedPassword = generateRandomPassword(8);

        // Sauvegarder le club et envoyer l'email
        Club savedClub = clubRepository.save(club);

        // Envoyer l'email
        String subject = "Informations d'authentification pour votre nouveau club";
        String text = "Bonjour,\n\nVotre club a été créé avec succès.\n" +
                "Nom d'utilisateur : admin\n" +
                "Mot de passe : " + generatedPassword + "\n\n" +
                "Veuillez changer votre mot de passe après la première connexion.\n\n" +
                "Cordialement,\nExpert Dev Solutions";

        emailService.sendSimpleMessage(club.getEmail(), subject, text);

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
}

