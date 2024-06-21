package com.example.demo.service.impliments;
import java.security.SecureRandom;

import com.example.demo.persistance.dao.AdminstarteurRepository;
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
    private AdminstarteurRepository administrateurRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Club saveClub(ClubAppWebRequest clubAppWebRequest) {
        String generatedPassword = generateRandomPassword(8);

        // Save the club
        Club savedClub = clubRepository.save(clubAppWebRequest.getClub());

        // Set the club to the AppWeb entity and save it
        clubAppWebRequest.getAppWeb().setClub(savedClub);
        AppWeb savedAppWeb = appWebService.saveAppWeb(clubAppWebRequest.getAppWeb());

        // Set the AppWeb entity to the Club entity and save the Club again
        savedClub.setAppWeb(savedAppWeb);
        clubRepository.save(savedClub);
        Administrateur admin = new Administrateur();
        admin.setUsername("admin"); // Set username for admin
        admin.setPassword(generatedPassword); // Set generated password for admin
        admin.setRole("admin"); // Set role for admin
        admin.setClub(savedClub);
        administrateurRepository.save(admin);
        // Send email with credentials
        String subject = "Informations d'authentification pour votre nouveau club";
        String htmlBody = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 0; }" +
                ".container { padding: 20px; }" +
                "h1 { color: #333; }" +
                "p { font-size: 16px; color: #555; }" +
                ".logo { width: 50px; margin-bottom: 20px; }" + // Adjust logo size as needed
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<img src='https://scontent.ftun14-1.fna.fbcdn.net/v/t39.30808-6/291888548_441109058022997_1842427483752470346_n.png?_nc_cat=110&ccb=1-7&_nc_sid=5f2048&_nc_ohc=9sCyIIEYrhsQ7kNvgEi0sN5&_nc_ht=scontent.ftun14-1.fna&oh=00_AYDMRgL_D9JXFw_K98l4nzC7mJwGellHAUhm9gCQUWSW-A&oe=667704EF' alt='Logo' class='logo'>" + // Add the logo URL
                "<h1>Bonjour,</h1>" +
                "<p>Votre club a été créé avec succès.</p>" +
                "<p><strong>Nom d'utilisateur : admin</strong></p>" +
                "<p><strong>Mot de passe : " + generatedPassword + "</strong></p>" +
                "<p>Veuillez changer votre mot de passe après la première connexion.</p>" +
                "<a href='http://localhost:4200/"+savedAppWeb.getAdresseUrl()+"/loginClub' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffff; background-color: #fcbc04; text-decoration: none; border-radius: 5px;'>Connectez-vous</a>" +
                "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            emailService.sendHtmlMessage(clubAppWebRequest.getClub().getEmail(), subject, htmlBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

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

