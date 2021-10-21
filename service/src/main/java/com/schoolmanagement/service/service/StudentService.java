package com.schoolmanagement.service.service;

import com.schoolmanagement.service.dto.StudentDto;
import com.schoolmanagement.service.exception.StudentNotFoundException;
import com.schoolmanagement.service.mapper.StudentMapper;
import com.schoolmanagement.service.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<StudentDto> findAll(){
        List<StudentDto> myList=repository.findAll()
                .stream()
                .map(StudentMapper::studentToStudentDto)
                .collect(Collectors.toList());

        return myList;
    }

    @Transactional(timeout = 1)
    public List<StudentDto> findAllStudentsWithTransactions(){
        repository.wasteTime();
        return repository.findAll()
                .stream()
                .map(student -> {
                    if(student!=null){
                        return StudentMapper.studentToStudentDto(student);
                    }
                    else
                        throw new StudentNotFoundException(-1);
                })
                .collect(Collectors.toList());
    }

    public StudentDto findStudentById(int id){
        return repository.findById(id)
                .map(StudentMapper::studentToStudentDto)
                .orElseThrow(()->new StudentNotFoundException(id));
    }

    public StudentDto saveStudent(StudentDto student){
        return StudentMapper
                .studentToStudentDto(repository
                        .save(StudentMapper
                                .studentDtoToStudent(student)));
    }

    public StudentDto updateStudent(Integer id,StudentDto student){
        return repository.findById(id)
                .map(student1->{
                    student1.setName(student.getName());
                    student1.setLastName(student.getLastName());
                    return StudentMapper.studentToStudentDto(repository.save(student1));
                }).orElseGet(()->{
                    student.setId(id);
                    return StudentMapper
                            .studentToStudentDto(repository
                                    .save(StudentMapper
                                            .studentDtoToStudent(student)));
                });
    }

    public void deleteStudent(Integer id){
         repository.deleteById(id);
    }
}
