package com.schoolmanagement.service.repository;

import com.schoolmanagement.service.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
