package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.entities.Administrateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AdministrateurServiceTests {

    @Mock
    private AdminstarteurRepository adminstarteurRepository;

    @InjectMocks
    private AdministrateurService administrateurService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAdminstrateur() {
        Administrateur administrateur = new Administrateur();
        administrateur.setNom("John Doe");
        when(adminstarteurRepository.save(any(Administrateur.class))).thenReturn(administrateur);
        Administrateur savedAdministrateur = administrateurService.saveAdminstrateur(administrateur);

        assertThat(savedAdministrateur.getNom()).isEqualTo("John Doe");
        verify(adminstarteurRepository, times(1)).save(administrateur);
    }

    @Test
    void testGetListAdminstrateurs() {
        Administrateur admin1 = new Administrateur();
        admin1.setNom("John Doe");
        Administrateur admin2 = new Administrateur();
        admin2.setNom("Jane Doe");

        List<Administrateur> admins = Arrays.asList(admin1, admin2);
        when(adminstarteurRepository.findAll()).thenReturn(admins);

        List<Administrateur> result = administrateurService.getListAdminstarteur();

        assertThat(result).hasSize(2);
    verify(adminstarteurRepository, times(1)).findAll();
}

@Test
    void testGetAdminstarteurByIdAdminstarteur() {
        Administrateur administrateur = new Administrateur();
        administrateur.setIdUtilisateur(1);
        administrateur.setNom("John Doe");

        when(adminstarteurRepository.findById(1L)).thenReturn(Optional.of(administrateur));

        Administrateur result = administrateurService.getAdminstarteurByIdAdminstarteur(1L);

        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("John Doe");
        verify(adminstarteurRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateAdminstarteur() {
        Administrateur administrateur = new Administrateur();
        administrateur.setIdUtilisateur(1);
        administrateur.setNom("John Doe");

        when(adminstarteurRepository.saveAndFlush(any(Administrateur.class))).thenReturn(administrateur);

        Administrateur updatedAdministrateur = administrateurService.updateAdminstarteur(administrateur);

        assertThat(updatedAdministrateur.getNom()).isEqualTo("John Doe");
        verify(adminstarteurRepository, times(1)).saveAndFlush(administrateur);
    }

    @Test
    void testDeleteAdminstarteur() {
        Long id = 1L;

        doNothing().when(adminstarteurRepository).deleteById(id);

        boolean isDeleted = administrateurService.deleteAdminstarteur(id);

        assertThat(isDeleted).isTrue();
        verify(adminstarteurRepository, times(1)).deleteById(id);
    }
}
