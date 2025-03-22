package com.lucas.projeto_spring_jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.projeto_spring_jpa.entities.OrderItem;
import com.lucas.projeto_spring_jpa.entities.pk.OrderItemPK;


public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
    
}