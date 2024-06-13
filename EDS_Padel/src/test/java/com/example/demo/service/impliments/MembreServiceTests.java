package com.example.demo.service.impliments;
import com.example.demo.persistance.dao.MembreRepository;
import com.example.demo.persistance.entities.Membre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MembreServiceTests {
    @Mock
    private MembreRepository membreRepository;

    @InjectMocks
    private MembreService membreService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSaveMembre() {
        Membre membre = new Membre();
        membre.setNom("test");
        when(membreRepository.save(any(Membre.class))).thenReturn(membre);
        Membre savedMembre = membreRepository.save(membre);

        assertThat(savedMembre.getNom()).isEqualTo("test");
        verify(membreRepository, times(1)).save(membre);
    }
    @Test
    void testGetListMembre() {
        Membre membre1 = new Membre();
        membre1.setNom("test1");
        Membre membre2 = new Membre();
        membre2.setNom("test2");

        List<Membre> membres = Arrays.asList(membre1, membre2);
        when(membreRepository.findAll()).thenReturn(membres);

        List<Membre> result = membreService.getListMembre();

        assertThat(result).hasSize(2);
        verify(membreRepository, times(1)).findAll();
    }
    @Test
    void testGetMembreByIdMembre() {
        Membre membre = new Membre();
        membre.setIdUtilisateur(1L);
        membre.setNom("Membre");
        when(membreRepository.findById(1L)).thenReturn(Optional.of(membre));
        Membre result = membreService.getMembreByIdMembre(1L);
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("Membre");
        verify(membreRepository, times(1)).findById(1L);
    }
    @Test
    void testUpdateMembre() {
        Membre membre = new Membre();
        membre.setIdUtilisateur(1L);
        membre.setNom("Membre");

        when(membreRepository.saveAndFlush(any(Membre.class))).thenReturn(membre);

        Membre updateMembre = membreService.updateMembre(membre);

        assertThat(updateMembre.getNom()).isEqualTo("Membre");
        verify(membreRepository, times(1)).saveAndFlush(membre);
    }

    @Test
    void testDeleteMembre() {
        Long id = 1L;

        doNothing().when(membreRepository).deleteById(id);

        boolean isDeleted = membreService.deleteMembre(id);

        assertThat(isDeleted).isTrue();
        verify(membreRepository, times(1)).deleteById(id);
    }
}
