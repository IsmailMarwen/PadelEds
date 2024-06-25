package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.AppWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppWebRepository extends JpaRepository<AppWeb,Long> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AppWeb a WHERE a.adresseUrl = :adresseUrl")
    boolean existsByAdresseUrl(String adresseUrl);
}
