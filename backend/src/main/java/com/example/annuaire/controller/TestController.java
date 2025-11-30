package com.example.annuaire.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.annuaire.model.Utilisateur;
import com.example.annuaire.repository.UtilisateurRepository;

@RestController
public class TestController {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public TestController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/")
    public String home() {
        return "Bienvenue sur l'API Annuaire! Essayez /api/test";
    }

    @GetMapping("/api/test")
    public String testDatabase() {
        Utilisateur u = new Utilisateur("aimad", "aimad", "1234");
        utilisateurRepository.save(u);
        return "utilisateur enregistre";
    }
}
