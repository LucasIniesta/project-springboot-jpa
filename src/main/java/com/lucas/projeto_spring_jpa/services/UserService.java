package com.lucas.projeto_spring_jpa.services;

import com.lucas.projeto_spring_jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.lucas.projeto_spring_jpa.entities.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User insert(User user) {
        return repository.save(user);
    }
}
