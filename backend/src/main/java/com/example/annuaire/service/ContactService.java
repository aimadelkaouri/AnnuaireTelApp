package com.example.annuaire.service;

import java.util.List;
import java.util.Optional;

import com.example.annuaire.model.Contact;

public interface ContactService {
    List<Contact> findAll();
    Optional<Contact> findById(Long id);
    Contact create(Contact contact);
    Contact update(Long id, Contact contact);
    void delete(Long id);
    List<Contact> searchByNom(String nom);
}
