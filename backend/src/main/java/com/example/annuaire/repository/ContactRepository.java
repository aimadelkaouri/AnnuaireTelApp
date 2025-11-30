package com.example.annuaire.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.annuaire.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    
}
