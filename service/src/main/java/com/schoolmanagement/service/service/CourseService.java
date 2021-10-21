package com.schoolmanagement.service.service;

import com.schoolmanagement.service.dto.CourseDto;

import java.util.List;

public interface CourseService {
    List<CourseDto> getAllCourses();
    CourseDto findCourse(Integer id);
    CourseDto saveCourse(CourseDto course);
    CourseDto updateCourse(Integer id,CourseDto courseDto);

    CourseDto patchCourse(Integer id, CourseDto courseDto);
    void deleteCourseById(Integer id);
}
