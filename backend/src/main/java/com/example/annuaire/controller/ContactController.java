package com.example.annuaire.controller;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.annuaire.service.ContactService;
import com.example.annuaire.service.UtilisateurService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.annuaire.model.Contact;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.annuaire.dto.ContactDto;
import com.example.annuaire.model.Utilisateur;

import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactService contactService;
    private final UtilisateurService utilisateurService;

    public ContactController(ContactService contactService, UtilisateurService utilisateurService){
        this.contactService = contactService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Contact> all() {
        return contactService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Contact> get(@PathVariable Long id) {
        return contactService.findById(id)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Contact> search(@RequestParam String q) {
        return contactService.searchByNom(q);
    }


    @PostMapping
    public ResponseEntity<Contact> create(@RequestBody ContactDto dto) {
        Utilisateur owner = utilisateurService.findById(dto.utilisateurId)
                                .orElseThrow(()-> new IllegalArgumentException("Utilisateur introuvable"));
        
        Contact c = new Contact();
        c.setNom(dto.nom);
        c.setPrenom(dto.prenom);
        c.setTelephone(dto.telephone);
        c.setEmail(dto.email);
        c.setPoste(dto.intitulePoste);
        c.setDirection(dto.direction);
        c.setBureau(dto.bureau);
        c.setUtilisateur(owner);

        Contact created = contactService.create(c);
        return ResponseEntity.created(URI.create("/api/contacts/"+
            created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Contact> update(@PathVariable Long id, @RequestBody  ContactDto dto) {
        return contactService.findById(id)
            .map(existing ->{
                existing.setNom(dto.nom);
                existing.setPrenom(dto.prenom);
                existing.setTelephone(dto.telephone);
                existing.setEmail(dto.email);
                existing.setPoste(dto.intitulePoste);
                existing.setDirection(dto.direction);
                existing.setBureau(dto.bureau);

                if(dto.utilisateurId != null){
                            Utilisateur owner = utilisateurService.findById(dto.utilisateurId)
                                .orElseThrow(()-> new IllegalArgumentException("Utilisateur introuvable"));
                                existing.setUtilisateur(owner);
                }
                return ResponseEntity.ok(contactService.update(id, existing));
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
    if (contactService.findById(id).isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    
    contactService.delete(id);
    return ResponseEntity.noContent().build();
}

    
    
}
