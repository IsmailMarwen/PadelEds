package com.example.demo.persistance.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Administrateur extends Utilisateur {
    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Notification> notifications;
}
