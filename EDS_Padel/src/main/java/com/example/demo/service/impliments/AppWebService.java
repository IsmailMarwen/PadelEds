package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.AppWebRepository;
import com.example.demo.persistance.entities.AppWeb;
import com.example.demo.service.interfaces.IAppWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AppWebService implements IAppWeb {
    @Autowired
    public AppWebRepository appWebRepository;
    @Override
    public AppWeb saveAppWeb(AppWeb appWeb) {
        return appWebRepository.save(appWeb);
    }

    @Override
    public AppWeb updateAppWeb(AppWeb appWeb) {
        AppWeb existingAppWeb = appWebRepository.findById(appWeb.getIdAppWeb())
                .orElseThrow(() -> new EntityNotFoundException("AppWeb not found"));

        // Update the fields of the existing AppWeb instance if they are not null
        if (appWeb.getNomAppWeb() != null) {
            existingAppWeb.setNomAppWeb(appWeb.getNomAppWeb());
        }
        if (appWeb.getLogoAppWeb() != null) {
            existingAppWeb.setLogoAppWeb(appWeb.getLogoAppWeb());
        }
        if (appWeb.getCouleurAppWeb() != null) {
            existingAppWeb.setCouleurAppWeb(appWeb.getCouleurAppWeb());
        }
        if (appWeb.getBannerImage() != null) {
            existingAppWeb.setBannerImage(appWeb.getBannerImage());
        }
        if (appWeb.getAdresseUrl() != null) {
            existingAppWeb.setAdresseUrl(appWeb.getAdresseUrl());
        }
        if (appWeb.getMode() != null) {
            existingAppWeb.setMode(appWeb.getMode());
        }
        if (appWeb.getCouleurSideBar() != null) {
            existingAppWeb.setCouleurSideBar(appWeb.getCouleurSideBar());
        }
        if (appWeb.getClub() != null) {
            existingAppWeb.setClub(appWeb.getClub());
        }

        return appWebRepository.saveAndFlush(existingAppWeb);
    }


    @Override
    public boolean deleteAppWeb(Long id) {
        appWebRepository.deleteById(id);
        return true;
    }

    @Override
    public List<AppWeb> getListAppWeb() {
        return appWebRepository.findAll();
    }

    @Override
    public AppWeb getAppWebByIdAppWeb(Long id) {
        return appWebRepository.findById(id).get();
    }
    public boolean existsByAdresseUrl(String adresseUrl) {
        return appWebRepository.existsByAdresseUrl(adresseUrl);
    }
}
