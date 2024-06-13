package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Club;

import java.util.List;

public interface IClub {
    Club saveClub(Club club);
    Club updateClub(Club club);
    boolean deleteClub(Long id);
    List<Club> getListClub();
    Club getClubByIdClub(Long id);
    public List<Club> getClubsNearLocation(double latitude, double longitude, double distance);
    public List<Club> getClubsByNomClub(String nomClub);
    public List<Club> getClubsByVille(String ville);

}
