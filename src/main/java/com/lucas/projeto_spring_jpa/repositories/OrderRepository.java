package com.lucas.projeto_spring_jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.projeto_spring_jpa.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
