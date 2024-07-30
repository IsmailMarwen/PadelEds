package com.example.demo.persistance.helper;

import com.example.demo.persistance.entities.MatchDetail;
import com.example.demo.persistance.entities.Reservation;

public class ReservationHelper {
    private MatchDetail match;
    private Reservation reservation;

    public MatchDetail getMatch() {
        return match;
    }

    public void setMatch(MatchDetail match) {
        this.match = match;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
