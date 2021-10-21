package com.schoolmanagement.service.service;

import com.schoolmanagement.service.config.LoadDatabase;
import com.schoolmanagement.service.dto.CourseDto;
import com.schoolmanagement.service.mapper.CourseMapper;
import com.schoolmanagement.service.model.Course;
import com.schoolmanagement.service.repository.CourseRepository;
import com.schoolmanagement.service.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseServiceImplTest {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    CourseService courseService;

    @Before
    public void setUp() throws Exception {

        LoadDatabase loadDatabase=new LoadDatabase();
        loadDatabase.commandLineRunner(studentRepository,courseRepository).run();
        courseService = new CourseServiceImpl(courseRepository,new CourseMapper());
    }

    private Integer getCourseIdValue(){
        return courseRepository.findAll().stream().findFirst().get().getId();
    }
    @Test
    public void patchCourseUpdateName() throws Exception{
        String updatedName="Updated Name";
        Integer id=getCourseIdValue();

        Course originalCourse=courseRepository.getOne(id);
        assertNotNull(originalCourse);

        String originalName=originalCourse.getName();

        CourseDto courseDto=new CourseDto();
        courseDto.setName(updatedName);

        courseService.patchCourse(id,courseDto);

        Course updatedCourse = courseRepository.findById(id).get();

        assertNotNull(updatedCourse);
        assertEquals(updatedName , updatedCourse.getName());
        assertNotEquals(updatedCourse.getName(),originalName);
    }
}