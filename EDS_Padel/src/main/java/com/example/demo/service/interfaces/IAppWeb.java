package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.AppWeb;

import java.util.List;

public interface IAppWeb {
    AppWeb saveAppWeb(AppWeb appWeb);
    AppWeb updateAppWeb(AppWeb appWeb);
    boolean deleteAppWeb(Long id);
    List<AppWeb> getListAppWeb();
    AppWeb getAppWebByIdAppWeb(Long id);
}
