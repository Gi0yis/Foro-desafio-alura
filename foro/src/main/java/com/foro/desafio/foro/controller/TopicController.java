package com.foro.desafio.foro.controller;

import com.foro.desafio.foro.domain.course.Course;
import com.foro.desafio.foro.domain.course.CourseRepository;
import com.foro.desafio.foro.domain.profile.DataProfileResponse;
import com.foro.desafio.foro.domain.profile.Profile;
import com.foro.desafio.foro.domain.profile.ProfileRepository;
import com.foro.desafio.foro.domain.topic.DataRegisterTopic;
import com.foro.desafio.foro.domain.topic.DataTopicResponse;
import com.foro.desafio.foro.domain.topic.Topic;
import com.foro.desafio.foro.domain.topic.TopicRepository;
import com.foro.desafio.foro.domain.user.User;
import com.foro.desafio.foro.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping("/{id}/{profileId}")
    public ResponseEntity<DataTopicResponse> registerTopic (@PathVariable Long id, @PathVariable Long profileId, @RequestBody DataRegisterTopic dataRegisterTopic, UriComponentsBuilder uriComponentsBuilder) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Course> courseOptional = courseRepository.findById(dataRegisterTopic.course().getId());

            if (courseOptional.isPresent()) {
                Optional<Profile> profileOptional = profileRepository.findById(profileId);
                if (profileOptional.isPresent()) {
                    Profile profile = profileOptional.get();
                    Course course = courseOptional.get();
                    Topic topic = new Topic(dataRegisterTopic.title(), dataRegisterTopic.message(), user, course);
                    topic.setCreationDate(LocalDateTime.now());
                    topic.setStatus(true);
                    Topic savedTopic = topicRepository.save(topic);

                    var registerTopicResponse = new DataTopicResponse(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), topic.getCourse().getId(), topic.getCourse().getCourseName(), profile.getId(), profile.getName());

                    var uri = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(savedTopic.getId()).toUri();
                    return ResponseEntity.created(uri).body(registerTopicResponse);
                } else {
                    System.out.println("Profile not found");
                }
            } else {
                System.out.println("Course not found");
            }
        } else {
            System.out.println("User not found");
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Topic>> findAll() {
        return ResponseEntity.ok(topicRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataTopicResponse> findTopicById(@PathVariable Long id) {
        Optional<Topic> topicOptional = topicRepository.findById(id);

        if (topicOptional.isPresent()) {
            Topic topicResponse = topicOptional.get();
            User user = topicResponse.getAuthor();
            Profile profile = user.getProfiles().iterator().next();

            var dataTopicResponse = new DataTopicResponse(topicResponse.getId(), topicResponse.getTitle(), topicResponse.getMessage(), topicResponse.getCreationDate(), topicResponse.getStatus(), topicResponse.getCourse().getId(), topicResponse.getCourse().getCourseName(), profile.getId(), profile.getName());
            return ResponseEntity.ok(dataTopicResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic updatedTopic) {
        Optional<Topic> topicOptional = topicRepository.findById(id);

        if (topicOptional.isPresent()) {
            Topic topic = topicOptional.get();
            topic.setTitle(updatedTopic.getTitle());
            topic.setMessage(updatedTopic.getMessage());

            Topic savedTopic = topicRepository.save(topic);
            return ResponseEntity.ok(savedTopic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTopic(@PathVariable Long id) {
        try {
            topicRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}