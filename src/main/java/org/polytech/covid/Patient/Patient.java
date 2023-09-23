package org.polytech.covid.Patient;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="t_patient")
public class Patient {
    @Id
    private Integer id;
    
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
    
    @Column(nullable = false)
    private LocalDate date;
}
