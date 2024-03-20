package org.example.jpa_hibernate.web;

import org.example.jpa_hibernate.entities.Patient;
import org.example.jpa_hibernate.repository.PatientRepository;
import org.example.jpa_hibernate.services.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {
    @Autowired
    private IHospitalService hospitalService;
    @GetMapping("/patients")
    public List<Patient> patientList(){
        return hospitalService.findPatientAll();
    }
}
