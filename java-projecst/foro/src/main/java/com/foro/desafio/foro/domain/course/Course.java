package com.foro.desafio.foro.domain.course;

import com.foro.desafio.foro.domain.profile.Profile;
import com.foro.desafio.foro.domain.topic.DataRegisterTopic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Course")
@Table(name = "courses")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String category;

    public Course(DataCreateCourse dataCreateCourse) {
        this.courseName = dataCreateCourse.courseName();
        this.category = dataCreateCourse.category();
    }
}
