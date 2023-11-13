package org.polytech.covid;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.polytech.covid.Patient.files.patient;
import org.polytech.covid.Patient.files.patientRepository;
import org.polytech.covid.Patient.service.patientService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.assertj.core.api.Assertions;

@SpringBootTest
public class patientServiceTest {
    @Autowired
    patientService patientService;

    @Autowired
    patientRepository patientRepository;

    // @Test
    // public void testAjoutSupprPatient() {
        
    //     patient patient = new patient();
    //     patient.setPrenom("jean");
    //     patient.setNom("dup");
    //     patient.setMail("jean.dupont@gmail.com");
    //     Long id = Long.parseLong("1");
    //     patient.setId_centre(id);
    //     patient.setVaccination(false);


    //     this.patientRepository.save(patient);

    //     // Assertions.assertThat(result).isTrue();
    //     // Assertions.assertThat(user.get()).isEqualTo(9);
    //     Assertions.assertThat(this.patientService.findAllByNom("dup").get(0).getMail()).isEqualTo("jean.dupont@gmail.com");
    //     Assertions.assertThat(this.patientService.findAllByNom("dup").get(0).getVaccination()).isFalse();
    //     this.patientRepository.delete(patient);
    //     Assertions.assertThat(this.patientService.findAllByNom("dup")).isEmpty();
    // }

    // @Test
    // public void testVaccinPatient() {
        
    //     patient patient = new patient();
    //     patient.setPrenom("jean");
    //     patient.setNom("dup");
    //     patient.setMail("jean.dupont@gmail.com");
    //     Long id = Long.parseLong("1");
    //     patient.setId_centre(id);
    //     patient.setVaccination(false);


    //     this.patientRepository.save(patient);


    //     patient updPatient = this.patientService.findAllByNom("dup").get(0);
    //     Assertions.assertThat(this.patientService.findAllByNom("dup").get(0).getVaccination()).isFalse();

    //     updPatient.setVaccination(true);
        
    //     this.patientService.update(updPatient);

    //     Assertions.assertThat(this.patientService.findAllByNom("dup").get(0).getMail()).isEqualTo("jean.dupont@gmail.com");
    //     Assertions.assertThat(this.patientService.findAllByNom("dup").get(0).getVaccination()).isTrue();

    //     this.patientRepository.delete(updPatient);
    //     Assertions.assertThat(this.patientService.findAllByNom("dup")).isEmpty();
    // }
  
    // @Test
    // public void testAjoutPatient() {
        
    //     patient patient = new patient();
    //     patient.setPrenom("jean");
    //     patient.setNom("fup");
    //     patient.setMail("jean.fup@gmail.com");
    //     Long id = Long.parseLong("1");
    //     patient.setId_centre(id);
    //     patient.setVaccination(false);


    //     this.patientRepository.save(patient);


    //     Assertions.assertThat(this.patientService.findAllByNom("fup").get(0).getMail()).isEqualTo("jean.fup@gmail.com");
    //     // this.patientRepository.delete(patient);
    //     // Assertions.assertThat(this.patientService.findAllByNom("dup")).isEmpty();
    // }


    
}
