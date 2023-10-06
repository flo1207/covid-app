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
    private long idPatient;
    
    @Column(nullable = false)
    private String mail; 
    
    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private Long id_centre;

    @Column(nullable = false)
    private Boolean vaccination;
    
    @Column(nullable = true)
    private LocalDate date_RDV;

    public Patient(String mail, String nom, String prenom, Long id_centre, Boolean vaccination,
            LocalDate date_RDV) {
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.id_centre = id_centre;
        this.vaccination = vaccination;
        this.date_RDV = date_RDV;
    }

    public Patient(){}

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public Long getid_centree() {
        return id_centre;
    }

    public void setNom_centre(Long id_centre) {
        this.id_centre = id_centre;
    }

    public LocalDate getDate_RDV() {
        return date_RDV;
    }

    public void setDate_RDV(LocalDate date_RDV) {
        this.date_RDV = date_RDV;
    }

    public Boolean getVaccination() {
        return vaccination;
    }

    public void setVaccination(Boolean vaccination) {
        this.vaccination = vaccination;
    }

    public long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(long idPatient) {
        this.idPatient = idPatient;
    }

    public Long getId_centre() {
        return id_centre;
    }

    public void setId_centre(Long id_centre) {
        this.id_centre = id_centre;
    }

}
