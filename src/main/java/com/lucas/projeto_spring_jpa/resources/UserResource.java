package com.lucas.projeto_spring_jpa.resources;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import com.lucas.projeto_spring_jpa.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> findAll() {
        User user = new User(1L, "Lucas", "l.iniesta.94@gmail.com" , "(41) 9 9146-1475", "12345");
        return ResponseEntity.ok().body(user);
    }
}
