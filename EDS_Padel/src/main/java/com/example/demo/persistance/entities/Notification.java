package com.example.demo.persistance.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime date;

    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Administrateur admin;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;
    @ManyToOne
    @JoinColumn(name = "membre_id")
    private Membre membre;
}
