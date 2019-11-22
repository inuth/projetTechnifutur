package be.spring.bootProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.spring.bootProject.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
