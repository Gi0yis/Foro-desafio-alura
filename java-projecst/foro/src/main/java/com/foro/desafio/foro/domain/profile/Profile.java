package com.foro.desafio.foro.domain.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foro.desafio.foro.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "Profile")
@Table(name = "profiles")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "profiles")
    private Set<User> users;

    public Profile(DataCreateProfile dataCreateProfile) {
        this.name = dataCreateProfile.name();
    }
}
