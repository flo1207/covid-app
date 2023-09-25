package org.polytech.covid.Patient.files;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="t_patient")
public class Patient {
    @Id
    @GeneratedValue
    private long id;
    
    @Column(nullable = false)
    private String mail; 
    
    @Column(nullable = false)
    private Integer num_tel;
    
    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom_centre;
    
    @Column(nullable = true)
    private LocalDate date;

    public Patient(){}

    public Patient(String mail, Integer num_tel, String nom, String prenom, String nom_centre, LocalDate date) {
        this.mail = mail;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.nom_centre = nom_centre;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(Integer num_tel) {
        this.num_tel = num_tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom_centre() {
        return nom_centre;
    }

    public void setNom_centre(String nom_centre) {
        this.nom_centre = nom_centre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
