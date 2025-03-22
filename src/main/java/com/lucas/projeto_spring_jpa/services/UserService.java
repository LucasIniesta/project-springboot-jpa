package com.lucas.projeto_spring_jpa.services;

import com.lucas.projeto_spring_jpa.repositories.UserRepository;
import com.lucas.projeto_spring_jpa.services.exceptions.ResourceNotFindException;

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
        return repository.findById(id).orElseThrow(() -> new ResourceNotFindException(id));
    }

    public User insert(User user) {
        return repository.save(user);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public User update(Long id, User user) {
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
