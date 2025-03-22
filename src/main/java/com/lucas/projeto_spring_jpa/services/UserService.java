package com.lucas.projeto_spring_jpa.services;

import com.lucas.projeto_spring_jpa.repositories.UserRepository;
import com.lucas.projeto_spring_jpa.services.exceptions.DabaseException;
import com.lucas.projeto_spring_jpa.services.exceptions.ResourceNotFindException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

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
        return repository.findById(id).orElseThrow(() -> new ResourceNotFindException(id));
    }

    public User insert(User user) {
        return repository.save(user);
    }

    public void deleteById(Long id) {
        
        if(!repository.existsById(id)){
            throw new ResourceNotFindException("User not found with id: " + id);
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DabaseException(e.getMessage());
        }
    }

    public User update(Long id, User user) {
        
        if(!repository.existsById(id)){
            throw new ResourceNotFindException("User not found with id: " + id);
        }
        User entity = repository.getReferenceById(id);
        updateData(entity, user);
        return repository.save(entity);
    }

    private void updateData(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
    }
}
