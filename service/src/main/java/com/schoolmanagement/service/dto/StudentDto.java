package com.schoolmanagement.service.dto;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

public class StudentDto {
    private int id;
    @ApiModelProperty(value = "Student name",required = true)
    private String name;
    private String lastName;
    private LocalDate dob;

    public StudentDto(int id,String name, String lastName, LocalDate dob) {
        this.id=id;
        this.name = name;
        this.lastName = lastName;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }


}
