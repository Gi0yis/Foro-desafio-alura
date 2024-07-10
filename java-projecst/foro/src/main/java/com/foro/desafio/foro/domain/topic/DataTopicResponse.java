package com.foro.desafio.foro.domain.topic;

import java.time.LocalDateTime;

public record DataTopicResponse(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        Boolean status,
        Long courseId,
        String courseName,
        Long authorId,
        String authorName
) {
}
