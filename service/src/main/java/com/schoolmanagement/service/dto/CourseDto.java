package com.schoolmanagement.service.dto;

import io.swagger.annotations.ApiModelProperty;

public class CourseDto {
    private int id;
    //swagger özelleştirmesi
    @ApiModelProperty(value = "kursun adı",required = true)
    private String name;
    private String url;

    public CourseDto(String name) {
        this.name = name;
    }

    public CourseDto() {
    }

    public CourseDto(int id, String name) {
        this.id=id;
        this.name = name;
    }

    public CourseDto(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public CourseDto setUrl(String url) {
        this.url = url;
        return this;
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
}
