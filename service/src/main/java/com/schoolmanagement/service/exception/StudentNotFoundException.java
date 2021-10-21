package com.schoolmanagement.service.exception;

//Geriye dönen response body'e hatayı ve hata kodunu buradan ayarlayabiliriz
//@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(int id) {
        super(id+ "id'li öğrenci bulunamamıştır");
    }
}
