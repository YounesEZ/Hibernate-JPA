package org.example.jpa_hibernate.repository;

import org.example.jpa_hibernate.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    public Patient findByNom(String name);
}
