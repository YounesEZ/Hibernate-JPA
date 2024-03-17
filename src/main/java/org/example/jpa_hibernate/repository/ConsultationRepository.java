package org.example.jpa_hibernate.repository;

import org.example.jpa_hibernate.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

}
