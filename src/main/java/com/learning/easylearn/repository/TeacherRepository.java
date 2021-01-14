package com.learning.easylearn.repository;

import com.learning.easylearn.entity.Course;
import com.learning.easylearn.entity.School;
import com.learning.easylearn.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByUsernameAndPassword(String username, String password);
    Boolean existsTeacherByUsername(String username);
    Teacher findByUsername(String username);
    Boolean existsByUsername(String username);
    List<Teacher> findAllByCourse(Course course);
    List<Teacher> findAllBySchool(School school);
}
