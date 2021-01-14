package com.learning.easylearn.services;

import com.learning.easylearn.DTO.CourseDto;
import com.learning.easylearn.entity.Course;
import com.learning.easylearn.repository.CourseRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    DozerBeanMapper mapper;
    CourseRepository courseRepository;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDto> listAllCourses() {
        return  courseRepository.findAll().stream().map(course -> mapper.map(course, CourseDto.class)).collect(Collectors.toList());
    }

    public void postCourse(CourseDto courseDto) {
        Course course = mapper.map(courseDto, Course.class);
        courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
