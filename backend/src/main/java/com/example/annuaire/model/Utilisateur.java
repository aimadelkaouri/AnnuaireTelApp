package com.example.annuaire.model;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nom;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String motDePasse;


    @OneToMany(mappedBy= "utilisateur", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Contact> contacts = new ArrayList<>();


    public Utilisateur(){

    }

    public Utilisateur( String nom, String email, String motDePasse){
        this.nom = nom;
        this.email= email;
        this.motDePasse= motDePasse;
    }

    public Long getId(){
        return id;
    }
        public String getNom(){
        return nom;
    }

            public void setNom(String nom){
        this.nom = nom;
    }

        public String getEmail(){
        return email;
    }

        public void setEmail(String email){
        this.email = email;
    }

        public String getMotDePasse(){
        return motDePasse;
    }

        public void setMotDePasse(String motDePasse){
        this.motDePasse = motDePasse;
    }
}
