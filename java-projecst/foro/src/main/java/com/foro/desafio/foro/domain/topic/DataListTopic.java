package com.foro.desafio.foro.domain.topic;

import com.foro.desafio.foro.domain.course.Course;
import com.foro.desafio.foro.domain.profile.Profile;

public record DataListTopic(
        String title,
        String message,
        String creationDate,
        Profile author,
        Course course
) {
}
