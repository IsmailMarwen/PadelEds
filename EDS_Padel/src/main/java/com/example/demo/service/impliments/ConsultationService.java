package com.example.demo.service.impliments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.persistance.dao.*;
import com.example.demo.persistance.entities.*;
import com.example.demo.service.interfaces.*;

@Service
public class ConsultationService implements IConsultation {
    @Autowired
    public ConsultationRepository consultationRepository;
    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation updateConsultation(Consultation consultation) {
        return consultationRepository.saveAndFlush(consultation);
    }

    @Override
    public boolean deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Consultation> getListConsultation() {
        return consultationRepository.findAll();
    }

    @Override
    public Consultation getConsultation(Long id) {
        return consultationRepository.findById(id).get();
    }

    @Override
    public int getQuantityOfConsultation() {
        return consultationRepository.getQuantityOfConsultation();
    }

    @Override
    public Consultation getConsultationById(Long id) {
        return null;
    }
}
