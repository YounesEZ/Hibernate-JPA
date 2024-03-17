package org.example.jpa_hibernate;

import org.example.jpa_hibernate.entities.*;
import org.example.jpa_hibernate.repository.ConsultationRepository;
import org.example.jpa_hibernate.repository.MedecinRepository;
import org.example.jpa_hibernate.repository.PatientRepository;
import org.example.jpa_hibernate.repository.RendezVousRepository;
import org.example.jpa_hibernate.services.HospitalServiceImpl;
import org.example.jpa_hibernate.services.IHospitalService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;


@SpringBootApplication
public class HospitalApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }
    @Bean
    CommandLineRunner start(IHospitalService hospitalService, RendezVousRepository rendezVousRepository){
        return args -> {
            //Creation des patients :

            Stream.of("Amine","Omar","Adam")
                    .forEach(name -> {
                        Patient patient = new Patient();
                        patient.setNom(name);
                        patient.setDateNaissance(new Date());
                        patient.setMalade(false);
                        hospitalService.savePatient(patient);
                    });

            //Creation des medecin :

            Stream.of("Hamza","Yasmine","Ahmed")
                    .forEach(name -> {
                        Medecin medecin = new Medecin();
                        medecin.setNom(name);;
                        medecin.setEmail(name.toLowerCase() + "@gmail.com");
                        medecin.setSpecialite((Math.random() >= 0.5)? "Cardio":"Dentiste");
                        hospitalService.saveMedecin(medecin);
                    });

            //Creation d'un rendez-vous :

            Patient patient = hospitalService.findPatientById(Long.valueOf(1));
            Medecin medecin = hospitalService.findMedecinById(Long.valueOf(2));

            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);
            hospitalService.saveRDV(rendezVous);


            //Creation d'une consultation :

            RendezVous rendezVous1 = rendezVousRepository.findAll().get(0);

            Consultation consultation = new Consultation();
            consultation.setDateConsultation(rendezVous1.getDate());
            consultation.setRapport("Rapport de consultation .....");
            consultation.setRendezVous(rendezVous1);
            hospitalService.saveConsultation(consultation);



        };
    }
}
