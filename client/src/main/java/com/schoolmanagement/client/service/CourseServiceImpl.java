package com.schoolmanagement.client.service;

import com.schoolmanagement.client.dto.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final String GET_COURSES="http://localhost:8080/courses";

    private final RestTemplate restTemplate;

    public CourseServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Course> getAllCourses() {
        ResponseEntity<List> data=restTemplate.getForEntity(GET_COURSES,List.class);
        List<Course> courses=data.getBody();
        return courses;
    }

    @Override
    public Course getCourse(Integer id) {
        return null;
    }

    @Override
    public Course saveCourse(Course course) {
        return null;
    }

    @Override
    public Course updateCourse(Integer id, Course course) {
        return null;
    }

    @Override
    public Course patchCourse(Integer id, Course course) {
        return null;
    }

    @Override
    public void deleteCourse(Integer id) {

    }
}
