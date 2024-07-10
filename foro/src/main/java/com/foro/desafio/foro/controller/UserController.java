package com.foro.desafio.foro.controller;

import com.foro.desafio.foro.domain.user.DataCreateUser;
import com.foro.desafio.foro.domain.user.DataUserResponse;
import com.foro.desafio.foro.domain.user.User;
import com.foro.desafio.foro.domain.user.UserRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<DataUserResponse> createUser(@RequestBody DataCreateUser dataCreateUser, UriComponentsBuilder uriComponentsBuilder) {
        User user = userRepository.save(new User(dataCreateUser));
        DataUserResponse dataUserResponse = new DataUserResponse(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getStatus());
        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(dataUserResponse);
    }
}
