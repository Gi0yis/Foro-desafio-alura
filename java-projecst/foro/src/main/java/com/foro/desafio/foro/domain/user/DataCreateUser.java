package com.foro.desafio.foro.domain.user;

public record DataCreateUser(
        String name,
        String email,
        String password,
        Boolean status
) {
}
