package com.foro.desafio.foro.controller;

import com.foro.desafio.foro.domain.profile.DataCreateProfile;
import com.foro.desafio.foro.domain.profile.DataProfileResponse;
import com.foro.desafio.foro.domain.profile.Profile;
import com.foro.desafio.foro.domain.profile.ProfileRepository;
import com.foro.desafio.foro.domain.user.User;
import com.foro.desafio.foro.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{userId}")
    public ResponseEntity<DataProfileResponse> createProfile(@PathVariable Long userId , @RequestBody DataCreateProfile dataCreateProfile, UriComponentsBuilder uriComponentsBuilder) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            var profile = profileRepository.save(new Profile(dataCreateProfile));
            user.getProfiles().add(profile);
            userRepository.save(user);

            DataProfileResponse dataProfileResponse = new DataProfileResponse(profile.getId(), user.getId(), profile.getName());

            var uri = uriComponentsBuilder.path("/profile/{id}").buildAndExpand(profile.getId()).toUri();
            return ResponseEntity.created(uri).body(dataProfileResponse);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
