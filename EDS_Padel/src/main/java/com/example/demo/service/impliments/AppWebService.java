package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.AppWebRepository;
import com.example.demo.persistance.entities.AppWeb;
import com.example.demo.service.interfaces.IAppWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return appWebRepository.saveAndFlush(appWeb);
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
}
