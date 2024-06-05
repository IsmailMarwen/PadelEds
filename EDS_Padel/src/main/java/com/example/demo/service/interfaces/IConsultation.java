package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Consultation;

import java.util.List;

public interface IConsultation {
    Consultation saveConsultation(Consultation consultation);
    Consultation updateConsultation(Consultation consultation);
    boolean deleteConsultation(Long id);
    List<Consultation> getListConsultation();
    Consultation getConsultation(Long id);
    int getQuantityOfConsultation();
    Consultation getConsultationById(Long id);
}