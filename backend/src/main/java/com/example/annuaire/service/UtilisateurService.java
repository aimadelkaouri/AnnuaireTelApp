package com.example.annuaire.service;

import java.util.List;
import java.util.Optional;

import com.example.annuaire.model.Utilisateur;

public interface UtilisateurService {
    List<Utilisateur> findAll();
    Optional<Utilisateur> findById(Long id);
    Utilisateur create(Utilisateur utilisateur);
}
