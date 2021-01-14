package com.learning.easylearn.repository;

import com.learning.easylearn.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsernameAndPassword(String username, String password);
    Boolean existsStudentByUsername(String username);
    Student findByUsername(String username);
}
