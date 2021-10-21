package com.schoolmanagement.service.mapper;

import com.schoolmanagement.service.dto.StudentDto;
import com.schoolmanagement.service.model.Student;

public class StudentMapper {
    public static StudentDto studentToStudentDto(Student student){
        StudentDto dto=new StudentDto(student.getId(),student.getName(),student.getLastName(),student.getDob());
        return dto;
    }

    public static Student studentDtoToStudent(StudentDto studentDto){
        Student student=new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setLastName(studentDto.getLastName());
        student.setDob(studentDto.getDob());
        return student;
    }
}
