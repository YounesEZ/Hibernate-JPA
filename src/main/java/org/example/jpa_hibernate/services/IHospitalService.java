package org.example.jpa_hibernate.services;

import org.example.jpa_hibernate.entities.*;

import java.util.List;

public interface IHospitalService {
    Patient savePatient(Patient patient);

    Patient findPatientById(Long id);

    List<Patient> findPatientAll();

    Medecin saveMedecin(Medecin medecin);

    Medecin findMedecinById(Long id);

    List<Medecin> findMedecinAll();

    RendezVous saveRDV(RendezVous rendezVous);

    RendezVous findRDVById(String id);

    List<RendezVous> findRDVAll();

    Consultation saveConsultation(Consultation consultation);

    Consultation findConsultationById(Long id);

    List<Consultation> findConsultationAll();

}