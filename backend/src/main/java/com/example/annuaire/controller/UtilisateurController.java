package com.example.annuaire.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.annuaire.service.UtilisateurService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.annuaire.model.Utilisateur;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    private final UtilisateurService service;
    public UtilisateurController(UtilisateurService service) {
        this.service = service;
    }

    @GetMapping
    public List<Utilisateur> all() {
        return service.findAll();
    }
    @PostMapping
    public ResponseEntity<Utilisateur> create(@RequestBody Utilisateur u) {
        Utilisateur created = service.create(u);
        return ResponseEntity.created(URI.create("/api/utilisateurs/" +created.getId())).body(created);
    }
    
    
}
