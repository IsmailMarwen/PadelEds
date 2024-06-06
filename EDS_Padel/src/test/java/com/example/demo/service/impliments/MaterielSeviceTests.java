package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.MaterielRepository;
import com.example.demo.persistance.entities.Materiel;
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

public class MaterielSeviceTests {
    @Mock
    private MaterielRepository materielRepository;

    @InjectMocks
    private MaterielService materielService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMateriel() {
        Materiel materiel = new Materiel();
        materiel.setReference("Materiel n1");

        when(materielRepository.save(any(Materiel.class))).thenReturn(materiel);

        Materiel savedMateriel = materielService.saveMateriel(materiel);

        assertThat(savedMateriel.getReference()).isEqualTo("Materiel n1");
        verify(materielRepository, times(1)).save(materiel);
    }

    @Test
    void testGetListMateriels() {
        Materiel admin1 = new Materiel();
        admin1.setReference("Materiel1");
        Materiel admin2 = new Materiel();
        admin2.setReference("Materiel2");

        List<Materiel> admins = Arrays.asList(admin1, admin2);
        when(materielRepository.findAll()).thenReturn(admins);

        List<Materiel> result = materielService.getListMateriel();

        assertThat(result).hasSize(2);
        verify(materielRepository, times(1)).findAll();
    }
    @Test
    void testGetMaterielByIdMateriel() {
        Materiel materiel = new Materiel();
        materiel.setIdMateriel(1);
        materiel.setReference("Materiel1");

        when(materielRepository.findById(1L)).thenReturn(Optional.of(materiel));

        Materiel result = materielService.getMaterielByIdMateriel(1L);

        assertThat(result).isNotNull();
        assertThat(result.getReference()).isEqualTo("Materiel1");
        verify(materielRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateMateriel() {
        Materiel materiel = new Materiel();
        materiel.setIdMateriel(1);
        materiel.setReference("Materiel1");

        when(materielRepository.saveAndFlush(any(Materiel.class))).thenReturn(materiel);

        Materiel updatedMateriel = materielService.updateMateriel(materiel);

        assertThat(updatedMateriel.getReference()).isEqualTo("Materiel1");
        verify(materielRepository, times(1)).saveAndFlush(materiel);
    }

    @Test
    void testDeleteMateriel() {
        Long id = 1L;

        doNothing().when(materielRepository).deleteById(id);

        boolean isDeleted = materielService.deleteMateriel(id);

        assertThat(isDeleted).isTrue();
        verify(materielRepository, times(1)).deleteById(id);
    }
}
