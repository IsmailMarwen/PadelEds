package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.AppWeb;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.helper.ClubAppWebRequest;

import java.util.List;

public interface IClub {
    Club saveClub(ClubAppWebRequest clubAppWebRequest);
    Club updateClub(Club club);
    boolean deleteClub(Long id);
    List<Club> getListClub();
    Club getClubByIdClub(Long id);
    public List<Club> getClubsNearLocation(double latitude, double longitude, double distance);
    public List<Club> getClubsByNomClub(String nomClub);
    public List<Club> getClubsByVille(String ville);

}
