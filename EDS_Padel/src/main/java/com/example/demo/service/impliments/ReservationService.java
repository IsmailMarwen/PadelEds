package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.ReservationRepository;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Reservation;
import com.example.demo.persistance.entities.Ressource;
import com.example.demo.service.interfaces.IReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservation {
        @Autowired
        public ReservationRepository reservationRepository;
        @Autowired
        public ClubService clubService;
        @Autowired
        public RessourceService ressourceService;
        @Override
        public Reservation saveReservation(Reservation reservation) {
            return reservationRepository.save(reservation);
        }

        @Override
        public Reservation updateReservation(Reservation reservation) {
            return reservationRepository.saveAndFlush(reservation);
        }

        @Override
        public boolean deleteReservation(Long id) {
            reservationRepository.deleteById(id);
            return true;
        }

        @Override
        public List<Reservation> getListReservation() {
            return reservationRepository.findAll();
        }

        @Override
        public Reservation getReservationByIdReservation(Long id) {
            return reservationRepository.findById(id).get();
        }

        @Override
        public List<Reservation> getListReservationByClub(Long idClub) {
            Club club=clubService.getClubByIdClub(idClub);
            return reservationRepository.getAllByClub(club);
        }

    @Override
    public List<Reservation> getListReservationByRessource(Long idRessource) {
        Ressource ressource=ressourceService.getRessourceByIdRessource(idRessource);
        return reservationRepository.getAllByRessource(ressource);
    }

    @Override
    public List<Reservation> getListByRessourceAndDate(Long idRessource, String date) {
        Ressource ressource=ressourceService.getRessourceByIdRessource(idRessource);
        return reservationRepository.getAllByRessource(ressource);    }


}
