package com.schoolmanagement.service.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(Integer id) {
        super(id+" numaralı kurs bulunamamıştır");
    }
}
