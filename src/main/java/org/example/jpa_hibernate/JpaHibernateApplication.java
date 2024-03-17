package org.example.jpa_hibernate;

import org.example.jpa_hibernate.entities.Product;
import org.example.jpa_hibernate.repository.ProductRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {
    @Autowired
    ProductRepositoty productRepositoty;
    public static void main(String[] args) {

        SpringApplication.run(JpaHibernateApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        productRepositoty.save(new Product(null,"Computer",4300.,3));
        productRepositoty.save(new Product(null,"Printer",1200.,4));
        productRepositoty.save(new Product(null,"Smart Phone",3200.,32));
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
    }
}
