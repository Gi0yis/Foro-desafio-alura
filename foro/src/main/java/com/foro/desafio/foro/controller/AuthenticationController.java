package com.foro.desafio.foro.controller;

import com.foro.desafio.foro.domain.infra.security.DatosJWTToken;
import com.foro.desafio.foro.domain.infra.security.TokenService;
import com.foro.desafio.foro.domain.user.DataAuthenticateUser;
import com.foro.desafio.foro.domain.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticate(@RequestBody @Valid DataAuthenticateUser dataAuthenticateUser) {
        Authentication authtoken = new UsernamePasswordAuthenticationToken(dataAuthenticateUser.email(), dataAuthenticateUser.password());
        var authUser = authenticationManager.authenticate(authtoken);
        var JWTToken = tokenService.generateToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTToken));
    }
}
