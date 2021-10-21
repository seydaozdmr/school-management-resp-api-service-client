package com.schoolmanagement.client.contoller;

import com.schoolmanagement.client.dto.Course;
import com.schoolmanagement.client.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseCountroller {
    private final CourseService courseService;

    public CourseCountroller(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }
}
