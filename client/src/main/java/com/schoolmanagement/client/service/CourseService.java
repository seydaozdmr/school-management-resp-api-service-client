package com.schoolmanagement.client.service;

import com.schoolmanagement.client.dto.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourse(Integer id);
    Course saveCourse(Course course);
    Course updateCourse(Integer id,Course course);
    Course patchCourse(Integer id,Course course);
    void deleteCourse(Integer id);
}
