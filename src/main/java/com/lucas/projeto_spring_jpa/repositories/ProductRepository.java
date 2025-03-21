package com.lucas.projeto_spring_jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.projeto_spring_jpa.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
