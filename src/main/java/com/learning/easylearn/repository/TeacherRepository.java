package com.learning.easylearn.repository;

import com.learning.easylearn.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByUsernameAndPassword(String username, String password);
    Boolean existsTeacherByUsername(String username);
}
