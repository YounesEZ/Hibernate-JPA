package org.example.jpa_hibernate.repository;

import org.example.jpa_hibernate.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {

}
