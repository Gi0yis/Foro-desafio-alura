package com.foro.desafio.foro.controller;

import com.foro.desafio.foro.domain.course.Course;
import com.foro.desafio.foro.domain.course.CourseRepository;
import com.foro.desafio.foro.domain.course.DataCourseResponse;
import com.foro.desafio.foro.domain.course.DataCreateCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<DataCourseResponse> registerCourse(@RequestBody DataCreateCourse dataCreateCourse, UriComponentsBuilder uriComponentsBuilder) {
        Course course = courseRepository.save(new Course(dataCreateCourse));
        DataCourseResponse dataCourseResponse = new DataCourseResponse(course.getId(), course.getCourseName(), course.getCategory());
        URI uri = uriComponentsBuilder.path("/course/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).body(dataCourseResponse);
    }
}
