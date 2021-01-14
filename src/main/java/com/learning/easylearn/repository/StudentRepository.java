package com.learning.easylearn.repository;

import com.learning.easylearn.entity.Course;
import com.learning.easylearn.entity.School;
import com.learning.easylearn.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsernameAndPassword(String username, String password);
    Boolean existsStudentByUsername(String username);
    Student findByUsername(String username);
    List<Student> findAllByCourse(Course course);
    List<Student> findAllBySchool(School school);
}
