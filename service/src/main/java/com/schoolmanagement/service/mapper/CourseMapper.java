package com.schoolmanagement.service.mapper;

import com.schoolmanagement.service.dto.CourseDto;
import com.schoolmanagement.service.model.Course;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseMapper {
    public CourseDto courseToCourseDto(Course course){
        return new CourseDto(course.getId(),course.getName());
    }

    public Course courseDtoToCourse(CourseDto courseDto){
        return new Course(courseDto.getId(),courseDto.getName());
    }
}
