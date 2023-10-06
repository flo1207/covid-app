package org.polytech.covid.User.files;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.polytech.covid.VaccinationCenter.files.VaccinationCentre;

import com.fasterxml.jackson.annotation.JsonBackReference;
 
@Entity
@Table(name="t_user")
public class User {
    @Id
    @GeneratedValue
    private long idUser;
    
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

    @OneToOne
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

    public VaccinationCentre getCenter() {
        return center;
    }

    public void setCenter(VaccinationCentre center) {
        this.center = center;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    // public VaccinationCentre getCenter(){
    //     return this.center;
        
    // }


}
