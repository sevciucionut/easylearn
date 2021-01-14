package com.learning.easylearn.services;

import com.learning.easylearn.DTO.UserLoginDto;
import com.learning.easylearn.DTO.UserRegisterDto;
import com.learning.easylearn.entity.Course;
import com.learning.easylearn.entity.School;
import com.learning.easylearn.entity.Student;
import com.learning.easylearn.repository.CourseRepository;
import com.learning.easylearn.repository.SchoolRepository;
import com.learning.easylearn.repository.StudentRepository;
import lombok.extern.java.Log;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    DozerBeanMapper mapper;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private SchoolRepository schoolRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository,
                                     CourseRepository courseRepository,
                                     SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.schoolRepository = schoolRepository;
    }

    public Boolean exist(String username) {
        return this.studentRepository.existsStudentByUsername(username);
    }

    public UserLoginDto login(String username, String password) {
        if (this.studentRepository.findByUsernameAndPassword(username, password) != null) {
            return mapper.map(this.studentRepository.findByUsernameAndPassword(username, password), UserLoginDto.class);
        } else {
            return null;
        }
    }

    public void register(UserRegisterDto userRegisterDto) {
        Student student = mapper.map(userRegisterDto, Student.class);
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public void asignSchoolToStudent(String username, Long schoolId, String title) {
        School school = schoolRepository.findByIdAndTitle(schoolId, title);
        Student student = studentRepository.findByUsername(username);
        student.setSchool(school);
        studentRepository.save(student);
    }

    public void asignCourseToStudent(String username, Long courseId, String title) {
        Course course = courseRepository.findByIdAndTitle(courseId, title);
        Student student = studentRepository.findByUsername(username);
        List<Course> courses = student.getCourse();
        courses.add(course);
        student.setCourse(courses);
        studentRepository.save(student);
    }
}
