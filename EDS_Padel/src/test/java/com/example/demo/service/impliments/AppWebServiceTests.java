package com.example.demo.service.impliments;
import com.example.demo.persistance.dao.AppWebRepository;
import com.example.demo.persistance.entities.AppWeb;
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

public class AppWebServiceTests {
    @Mock
    private AppWebRepository appWebRepository;

    @InjectMocks
    private AppWebService appWebService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSaveAppWeb() {
        AppWeb appWeb = new AppWeb();
        appWeb.setNomAppWeb("test");
        when(appWebRepository.save(any(AppWeb.class))).thenReturn(appWeb);
        AppWeb savedAppWeb = appWebRepository.save(appWeb);

        assertThat(savedAppWeb.getNomAppWeb()).isEqualTo("test");
        verify(appWebRepository, times(1)).save(appWeb);
    }
    @Test
    void testGetListAppWeb() {
        AppWeb appWeb1 = new AppWeb();
        appWeb1.setNomAppWeb("test1");
        AppWeb appWeb2 = new AppWeb();
        appWeb2.setNomAppWeb("test2");

        List<AppWeb> appWebs = Arrays.asList(appWeb1, appWeb2);
        when(appWebRepository.findAll()).thenReturn(appWebs);

        List<AppWeb> result = appWebService.getListAppWeb();

        assertThat(result).hasSize(2);
        verify(appWebRepository, times(1)).findAll();
    }
    @Test
    void testGetAppWebByIdAppWeb() {
        AppWeb appWeb = new AppWeb();
        appWeb.setIdAppWeb(1);
        appWeb.setNomAppWeb("AppWeb");

        when(appWebRepository.findById(1L)).thenReturn(Optional.of(appWeb));

        AppWeb result = appWebService.getAppWebByIdAppWeb(1L);

        assertThat(result).isNotNull();
        assertThat(result.getNomAppWeb()).isEqualTo("AppWeb");
        verify(appWebRepository, times(1)).findById(1L);
    }
    @Test
    void testUpdateAppWeb() {
        AppWeb appWeb = new AppWeb();
        appWeb.setIdAppWeb(1);
        appWeb.setNomAppWeb("AppWeb");

        when(appWebRepository.saveAndFlush(any(AppWeb.class))).thenReturn(appWeb);

        AppWeb updateAppWeb = appWebService.updateAppWeb(appWeb);

        assertThat(updateAppWeb.getNomAppWeb()).isEqualTo("AppWeb");
        verify(appWebRepository, times(1)).saveAndFlush(appWeb);
    }

    @Test
    void testDeleteAppWeb() {
        Long id = 1L;

        doNothing().when(appWebRepository).deleteById(id);

        boolean isDeleted = appWebService.deleteAppWeb(id);

        assertThat(isDeleted).isTrue();
        verify(appWebRepository, times(1)).deleteById(id);
    }

}
