package com.foro.desafio.foro.domain.topic;

import com.foro.desafio.foro.domain.course.Course;
import com.foro.desafio.foro.domain.profile.Profile;

public record DataRegisterTopic(
        String title,
        String message,
        String creationDate,
        Boolean status,
        Profile author,
        Course course
) {
}
