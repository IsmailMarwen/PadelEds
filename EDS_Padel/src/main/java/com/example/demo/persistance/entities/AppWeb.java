package com.example.demo.persistance.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AppWeb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAppWeb;
    private  String nomAppWeb;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String logoAppWeb;
    private String couleurAppWeb;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String bannerImage;
    private  String adresseUrl;
    private String mode;
    private String couleurSideBar;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idClub", referencedColumnName = "idClub")
    private Club club;
}
