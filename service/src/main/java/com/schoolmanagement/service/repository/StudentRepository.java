package com.schoolmanagement.service.repository;

import com.schoolmanagement.service.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    default int wasteTime() {
        int i = Integer.MIN_VALUE;
        while(i < Integer.MAX_VALUE) {
            i++;
        }
        return i;
    }
}
