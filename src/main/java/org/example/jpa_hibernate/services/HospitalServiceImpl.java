package org.example.jpa_hibernate.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.jpa_hibernate.entities.*;
import org.example.jpa_hibernate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class HospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    @Override
    public Patient savePatient(Patient patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    public Patient findPatientById(Long id) {
        return patientRepository.findById(id).get();
    }

    @Override
    public List<Patient> findPatientAll() {
        return patientRepository.findAll();
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return this.medecinRepository.save(medecin);
    }

    @Override
    public Medecin findMedecinById(Long id) {
        return medecinRepository.findById(id).get();
    }

    @Override
    public List<Medecin> findMedecinAll() {
        return medecinRepository.findAll();
    }

    @Override
    public RendezVous saveRDV(RendezVous rendezVous) {
        rendezVous.setId(UUID.randomUUID().toString());
        return this.rendezVousRepository.save(rendezVous);
    }

    @Override
    public RendezVous findRDVById(String id) {
        return rendezVousRepository.findById(id).get();
    }

    @Override
    public List<RendezVous> findRDVAll() {
        return rendezVousRepository.findAll();
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return this.consultationRepository.save(consultation);
    }

    @Override
    public Consultation findConsultationById(Long id) {
        return consultationRepository.findById(id).get();
    }

    @Override
    public List<Consultation> findConsultationAll() {
        return consultationRepository.findAll();
    }
}
