package com.learning.easylearn.services;

import com.learning.easylearn.DTO.CourseDto;
import com.learning.easylearn.entity.Course;
import com.learning.easylearn.entity.Student;
import com.learning.easylearn.entity.Teacher;
import com.learning.easylearn.repository.CourseRepository;
import com.learning.easylearn.repository.StudentRepository;
import com.learning.easylearn.repository.TeacherRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    DozerBeanMapper mapper;
    CourseRepository courseRepository;
    TeacherService teacherService;
    StudentRepository studentRepository;
    TeacherRepository teacherRepository;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository,
                                    TeacherService teacherService,
                                    StudentRepository studentRepository,
                                    TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<CourseDto> listAllCourses() {
        return courseRepository.findAll().stream().map(course -> mapper.map(course, CourseDto.class)).collect(Collectors.toList());
    }

    public void postCourse(String username, String title, String description) {
        Course course = new Course();
        course.setDescription(description);
        course.setTitle(title);
        courseRepository.save(course);
        teacherService.asignCourseToTeacher(username, course.getId(), course.getTitle());
    }

    public void deleteCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        course.ifPresent(value -> {
            List<Student> students = studentRepository.findAllByCourse(value);
            for (Student student : students) {
                student.setCourse(student.getCourse()
                        .stream()
                        .filter(curs -> !curs.equals(value))
                        .collect(Collectors.toList()));
                studentRepository.save(student);
            }
            List<Teacher> teachers = teacherRepository.findAllByCourse(value);
            for (Teacher teacher : teachers) {
                teacher.setCourse(teacher.getCourse()
                        .stream()
                        .filter(curs -> !curs.equals(value))
                        .collect(Collectors.toList()));
                teacherRepository.save(teacher);
            }
            courseRepository.delete(value);
        });
    }

    public void renameCourse(String title, String description, String currentTitle, String currentDescription) {
        Course course = courseRepository.findByTitleAndDescription(title, description);
        course.setTitle(currentTitle);
        course.setDescription(currentDescription);
        courseRepository.save(course);
    }
}
