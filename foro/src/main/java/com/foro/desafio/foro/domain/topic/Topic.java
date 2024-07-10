package com.foro.desafio.foro.domain.topic;

import com.foro.desafio.foro.domain.course.Course;
import com.foro.desafio.foro.domain.user.User;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity(name = "Topic")
@Table(name = "topics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;


    public Topic(DataRegisterTopic dataRegisterTopic) {
        this.title = dataRegisterTopic.title();
        this.message = dataRegisterTopic.message();
        this.creationDate = LocalDateTime.now();
        this.status = true;

        this.course = dataRegisterTopic.course();
    }

    public Topic(String title, String message, User user, Course course) {
        this.title = title;
        this.message = message;
        this.author = user;
        this.course = course;
    }
}
