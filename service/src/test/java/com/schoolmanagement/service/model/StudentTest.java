package com.schoolmanagement.service.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    Student student;

    @BeforeEach
    void setUp() {
        student=new Student();
        student.setId(1);
    }

    @Test
    void getId() {
        assertEquals(1,student.getId());
    }
}