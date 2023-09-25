package org.polytech.covid.VaccinationCenter.files;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.polytech.covid.Patient.files.Patient;

@Entity
@Table(name="t_vaccination_centre")
public class VaccinationCentre {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String city;

    @OneToMany
    private List<Patient> patients;

    public VaccinationCentre(){}

    public VaccinationCentre( String nom, String adresse, String city){
        this.nom = nom;
        this.adresse = adresse;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void addPatient(Patient patient) {
        List<Patient> patients = getPatients();
        patients.add(patient);
        this.patients = patients;
    }


}
