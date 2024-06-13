package com.example.demo.service.controller;
import com.example.demo.persistance.entities.AppWeb;
import com.example.demo.service.interfaces.IAppWeb;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/appWeb")
public class AppWebController {
    private final IAppWeb iAppWeb;
    public AppWebController(IAppWeb iAppWeb){
        this.iAppWeb=iAppWeb;
    }
    @PostMapping("/add")
    AppWeb save(@RequestBody AppWeb appWeb) {
        AppWeb a=iAppWeb.saveAppWeb(appWeb);
        return a ;
    }
    @PutMapping("/update")
    AppWeb update(@RequestBody AppWeb appWeb) {

        return iAppWeb.updateAppWeb(appWeb);
    }
    @GetMapping("/getAll")
    List<AppWeb> getAllAppWebs() {

        return iAppWeb.getListAppWeb();
    }

    @GetMapping("/getById/{id}")
    AppWeb getAppWebById(@PathVariable Long id) {

        return iAppWeb.getAppWebByIdAppWeb(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iAppWeb.deleteAppWeb(id);
        return true;
    }
}
