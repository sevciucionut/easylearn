package com.learning.easylearn.repository;

import com.learning.easylearn.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAll();

    Course findByIdAndTitle(Long id, String title);
    Course findByTitleAndDescription(String title, String description);
}
