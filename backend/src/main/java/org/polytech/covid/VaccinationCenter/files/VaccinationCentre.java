package org.polytech.covid.VaccinationCenter.files;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.polytech.covid.Patient.files.Patient;
import org.polytech.covid.User.files.User;

@Entity
@Table(name="t_vaccination_centre")
public class VaccinationCentre {
    @Id
    @GeneratedValue
    private long id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @OneToMany
    private List<Patient> patients;

    @OneToMany(mappedBy = "center")
    private List<User> users;

    public VaccinationCentre(){}

    public VaccinationCentre( String name, String address, String city){
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
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

    public List<User> getUsers() {
        return this.users;
    }

    public void addUser(User new_user) {
        List<User> users = getUsers();
        users.add(new_user);
        this.users = users;
    }


}
