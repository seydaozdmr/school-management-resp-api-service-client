package com.schoolmanagement.service.controller;

import com.schoolmanagement.service.dto.CourseDto;
import com.schoolmanagement.service.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "this is course controller")
@RestController
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //@ApiOperation: swagger özelleştirmesini sağlıyor
    @ApiOperation(value = "Bütün kursların liste olarak dönmesini sağlar",notes = "Bu method List\\<CourseDto>\\ olarak geri dönüş yapar.")
    @GetMapping("/courses")
    public List<CourseDto> getAll(){
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{id}")
    public CourseDto findCourse(@PathVariable Integer id){
        return courseService.findCourse(id);
    }

    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto saveCourse(@RequestBody CourseDto courseDto){
        return courseService.saveCourse(courseDto);
    }

    @PutMapping("/courses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDto updateCourse(@PathVariable Integer id, @RequestBody CourseDto courseDto){
        return courseService.updateCourse(id,courseDto);
    }

    @PatchMapping("/courses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDto patchCourse(@PathVariable Integer id,@RequestBody CourseDto courseDto){
        return courseService.patchCourse(id,courseDto);
    }

    @DeleteMapping("/courses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable Integer id){
        courseService.deleteCourseById(id);
    }

}
