package org.example.jpa_hibernate.services;

import org.example.jpa_hibernate.entities.Consultation;
import org.example.jpa_hibernate.entities.Medecin;
import org.example.jpa_hibernate.entities.Patient;
import org.example.jpa_hibernate.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Patient findPatientById(Long id);
    Medecin saveMedecin(Medecin medecin);
    Medecin findMedecinById(Long id);
    RendezVous saveRDV(RendezVous rendezVous);
    RendezVous findRDVById(String id);
    Consultation saveConsultation(Consultation consultation);
    Consultation findConsultationById(Long id);
}
