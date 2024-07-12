package com.example.demo.persistance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity

public class AgentAcceuil extends Utilisateur{
    @OneToMany(mappedBy = "agentAcceuil", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Notification> notifications;
}
