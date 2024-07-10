package com.foro.desafio.foro.domain.user;

public record DataUserResponse(
         Long id,
         String name,
         String email,
         String password,
         Boolean status
) {
}
