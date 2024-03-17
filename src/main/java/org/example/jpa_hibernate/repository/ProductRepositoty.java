package org.example.jpa_hibernate.repository;

import org.example.jpa_hibernate.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoty extends JpaRepository<Product,Long> {

}
