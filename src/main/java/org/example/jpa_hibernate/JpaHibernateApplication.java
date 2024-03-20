package org.example.jpa_hibernate;

import org.example.jpa_hibernate.entities.*;
import org.example.jpa_hibernate.repository.*;
import org.example.jpa_hibernate.services.IHospitalService;
import org.example.jpa_hibernate.services.IUserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;


@SpringBootApplication
public class JpaHibernateApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateApplication.class, args);
    }
    @Bean
    CommandLineRunner start(IHospitalService hospitalService, IUserService userService, ProductRepositoty productRepositoty){
        return args -> {
            //----------------------------------------Partie HospitalApplication----------------------
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

            RendezVous rendezVous1 = hospitalService.findRDVAll().get(0);

            Consultation consultation = new Consultation();
            consultation.setDateConsultation(rendezVous1.getDate());
            consultation.setRapport("Rapport de consultation .....");
            consultation.setRendezVous(rendezVous1);
            hospitalService.saveConsultation(consultation);

            //----------------------------------------Partie Users et Role----------------------

            User u1 = new User();
            u1.setUsername("user1");
            u1.setPassword("123456");
            userService.saveUser(u1);

            User u2 = new User();
            u2.setUsername("user2");
            u2.setPassword("123456");
            userService.saveUser(u2);

            Stream.of("STUDENT","USER","ADMIN").forEach(r ->{
                Role role = new Role();
                role.setRoleName(r);
                userService.saveRole(role);
            });

            userService.addRoletoUser("user1", "USER");
            userService.addRoletoUser("user1", "STUDENT");

            userService.addRoletoUser("user2", "STUDENT");
            userService.addRoletoUser("user2", "ADMIN");

            productRepositoty.save(new Product(null,"Computer",4300.,3));
            productRepositoty.save(new Product(null,"Printer",1200.,4));
            productRepositoty.save(new Product(null,"Smart Phone",3200.,32));

            try {
                User user = userService.authenticate("user1","16");

            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

            //----------------------------------------Partie Produits----------------------

            System.out.println("******************CONSULTER TOUT LES PRODUIT*****************************");

            List<Product> products = productRepositoty.findAll();
            products.forEach(p ->{
                System.out.println(p);
            });

            System.out.println("*******************CONSULTER UN PRODUIT****************************");
            Product product = productRepositoty.findById(Long.valueOf(1)).get();
            System.out.println(product);

            System.out.println("********************METTRE A JOUR DU PRIX UN PRODUIT***************************");
            product = productRepositoty.findById(Long.valueOf(2)).get();
            product.setPrice(2000.);
            productRepositoty.save(product);
            System.out.println(product);

            System.out.println("********************SUPPRIMER UN PRODUIT***************************");
            product = productRepositoty.findById(Long.valueOf(2)).get();
            productRepositoty.delete(product);
            products = productRepositoty.findAll();
            products.forEach(p ->{
                System.out.println(p);
            });

        };
    }
}
