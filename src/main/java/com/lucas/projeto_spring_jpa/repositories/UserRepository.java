package com.lucas.projeto_spring_jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.projeto_spring_jpa.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
