package com.schoolmanagement.client.contoller;

import com.schoolmanagement.client.dto.Student;
import com.schoolmanagement.client.service.AppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class StudentController {

    private final AppService appService;

    public StudentController(AppService appService) {
        this.appService = appService;
    }

    /**
     * /students ve /getStudents methodlarının ikisi de List<Student> listesini geriye dönüyor.
     */
    @GetMapping("/students")
    public List<Student> getStudents(){
        return appService.getStudents();
    }

    @GetMapping("/getStudents")
    public List<Student> getAllStudents() throws IOException {
        return appService.getAllStudents();
    }

    @GetMapping("/studentsArray")
    public ResponseEntity<List<Student>> getAll(){
        return new ResponseEntity<>(appService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/studentsWithHeader")
    public List<Student> getStudentsWithHeader(){
        return appService.getAllWithHeader();
    }

    @GetMapping("/studentsList")
    public ResponseEntity<List<Student>> studentList(){
        return ResponseEntity.ok(appService.getStudentListAsResponseEntity());
    }

    @GetMapping("/studentListObject")
    public ResponseEntity<List<Student>> studentListAsObject() {
        return ResponseEntity.ok(appService.getStudentListAsObject());
    }


    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Integer id){
        return appService.getStudent(id);
    }
    @GetMapping("/findStudent/{id}")
    public Student findStudent(@PathVariable Integer id){
        return appService.findStudent(id);
    }

    //creating student
    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student){
        return appService.saveStudent(student);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable Integer id,@RequestBody Student student){
        return appService.updateStudent(id,student);
    }

    @PutMapping("/updateStudent/{id}")
    public Student updateStudentState(@PathVariable Integer id,@RequestBody Student student){
        return appService.updateStudentState(id,student);
    }


}

