package com.schoolmanagement.service.service;

import com.schoolmanagement.service.dto.CourseDto;
import com.schoolmanagement.service.exception.CourseNotFoundException;
import com.schoolmanagement.service.mapper.CourseMapper;
import com.schoolmanagement.service.model.Course;
import com.schoolmanagement.service.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper mapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper mapper) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }


    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(mapper::courseToCourseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto findCourse(Integer id) {
        return courseRepository.findById(id)
                .map(mapper::courseToCourseDto)
                .map(e->e.setUrl("/courses/"+e.getId())).orElseThrow(()->new CourseNotFoundException(id));
    }

    @Override
    public CourseDto saveCourse(CourseDto course) {
//        return mapper
//                .courseToCourseDto(courseRepository.save(mapper.courseDtoToCourse(course)));
        return saveAndReturnDto(mapper.courseDtoToCourse(course));
    }

    private CourseDto saveAndReturnDto(Course course){
        Course savedCourse=courseRepository.save(course);
        CourseDto dto=mapper.courseToCourseDto(savedCourse);
        dto.setUrl("/courses/"+savedCourse.getId());
        return dto;
    }

    @Override
    public CourseDto updateCourse(Integer id, CourseDto courseDto) {
        Course course=mapper.courseDtoToCourse(courseDto);
        course.setId(id);
        return saveAndReturnDto(course);
    }

    @Override
    public CourseDto patchCourse(Integer id, CourseDto courseDto) {
        return courseRepository.findById(id).map(course->{
            if(courseDto.getName()!=null){
                course.setName(courseDto.getName());
            }
            CourseDto dto = mapper.courseToCourseDto(courseRepository.save(course));
            dto.setUrl("/courses/"+ id );
            return dto;
        }).orElseThrow(()-> new CourseNotFoundException(id));
    }

    @Override
    public void deleteCourseById(Integer id) {
        courseRepository.deleteById(id);
    }


}
