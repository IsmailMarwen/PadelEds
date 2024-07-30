package com.example.demo.persistance.helper;

import com.example.demo.persistance.entities.Match;
import com.example.demo.persistance.entities.Reservation;

public class ReservationHelper {
    private Match match;
    private Reservation reservation;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
