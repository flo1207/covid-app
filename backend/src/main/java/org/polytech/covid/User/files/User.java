package org.polytech.covid.User.files;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.polytech.covid.VaccinationCenter.files.VaccinationCentre;
 
@Entity
@Table(name="t_user")
public class User {
    @Id
    @GeneratedValue
    private long id;
    
    @Column(nullable = false)
    private String mail; 
    
    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer role;

    @ManyToOne
    private VaccinationCentre center;

    public User(String prenom, String nom, String password, String mail, VaccinationCentre center, Integer role) {
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.center = center;
        this.role = role;
    }

    public User(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }


}
