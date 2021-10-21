package com.schoolmanagement.client.service;

import com.schoolmanagement.client.dto.Student;

import java.io.IOException;
import java.util.List;

public interface AppService {
    List<Student> getStudents();
    List<Student> getAllStudents() throws IOException;
    List<Student> getAll();
    List<Student> getAllWithHeader();
    List<Student> getStudentListAsResponseEntity();
    List<Student> getStudentListAsObject();

    Student saveStudent(Student student);

    Student getStudent(Integer id);
    Student findStudent(Integer id);

    Student updateStudent(Integer id, Student student);
    Student updateStudentState(Integer id,Student student);
}
