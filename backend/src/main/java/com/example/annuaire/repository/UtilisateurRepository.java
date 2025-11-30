package com.example.annuaire.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.annuaire.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    
}
