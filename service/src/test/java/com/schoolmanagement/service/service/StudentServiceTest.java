package com.schoolmanagement.service.service;

import com.schoolmanagement.service.controller.StudentController;
import com.schoolmanagement.service.dto.StudentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class StudentServiceTest {
    @Mock
    StudentService service;

    @InjectMocks
    StudentController controller;


    MockMvc mockMvc;

    @BeforeEach
     void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAll() throws Exception {
        StudentDto s1=new StudentDto(1,"ali","veli", LocalDate.of(2000,10,9));
        StudentDto s2=new StudentDto(2,"murat","şeker",LocalDate.of(1987,2,3));

        List<StudentDto> students= Arrays.asList(s1,s2);
        when(service.findAll()).thenReturn(students);

        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/getStudents")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$",hasSize(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}