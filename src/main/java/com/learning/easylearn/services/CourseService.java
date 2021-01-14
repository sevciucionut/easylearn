package com.learning.easylearn.services;

import com.learning.easylearn.DTO.CourseDto;
import com.learning.easylearn.entity.Course;
import com.learning.easylearn.entity.Teacher;
import com.learning.easylearn.repository.CourseRepository;
import com.learning.easylearn.repository.TeacherRepository;
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
    TeacherService teacherService;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
    }

    public List<CourseDto> listAllCourses() {
        return  courseRepository.findAll().stream().map(course -> mapper.map(course, CourseDto.class)).collect(Collectors.toList());
    }

    public void postCourse(String username, String title, String description) {
        Course course = new Course();
        course.setDescription(description);
        course.setTitle(title);
        courseRepository.save(course);
        teacherService.asignCourseToTeacher(username, course.getId(), course.getTitle());
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public void renameCourse(String title, String description, String currentTitle, String currentDescription) {
        Course course = courseRepository.findByTitleAndDescription(title, description);
        course.setTitle(currentTitle);
        course.setDescription(currentDescription);
        courseRepository.save(course);
    }
}
