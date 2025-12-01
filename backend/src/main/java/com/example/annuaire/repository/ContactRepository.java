package com.example.annuaire.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.annuaire.model.Contact;
import com.example.annuaire.model.Utilisateur;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUtilisateur(Utilisateur utilisateur);
    List<Contact> findByNomContainingIgnoreCase(String nom);
}
