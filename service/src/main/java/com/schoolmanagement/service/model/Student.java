package com.schoolmanagement.service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String lastName;
    private LocalDate dob;

    public Student(){}

    public Student(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Student(String name, String lastName, LocalDate dob) {
        this.name = name;
        this.lastName = lastName;
        this.dob = dob;
    }



    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && name.equals(student.name) && lastName.equals(student.lastName) && dob.equals(student.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName , dob);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                '}';
    }
}
