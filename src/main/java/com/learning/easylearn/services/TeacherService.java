package com.learning.easylearn.services;

import com.learning.easylearn.DTO.UserLoginDto;
import com.learning.easylearn.DTO.UserRegisterDto;
import com.learning.easylearn.entity.Course;
import com.learning.easylearn.entity.School;
import com.learning.easylearn.entity.Student;
import com.learning.easylearn.entity.Teacher;
import com.learning.easylearn.repository.CourseRepository;
import com.learning.easylearn.repository.SchoolRepository;
import com.learning.easylearn.repository.TeacherRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    DozerBeanMapper mapper;
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;
    private SchoolRepository schoolRepository;

    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository,
                                     CourseRepository courseRepository,
                                     SchoolRepository schoolRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.schoolRepository = schoolRepository;
    }

    public Boolean exist(String username) {
        return this.teacherRepository.existsTeacherByUsername(username);
    }

    public UserLoginDto login(String username, String password) {
        if (teacherRepository.findByUsernameAndPassword(username, password) != null) {
            return mapper.map(teacherRepository.findByUsernameAndPassword(username, password), UserLoginDto.class);
        } else {
            return null;
        }
    }

    public void register(UserRegisterDto userRegisterDto) {
        Teacher teacher = mapper.map(userRegisterDto, Teacher.class);
        this.teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public void asignSchoolToTeacher(String username, Long courseId, String title) {
        School school = schoolRepository.findByIdAndTitle(courseId, title);
        Teacher teacher = teacherRepository.findByUsername(username);
        teacher.setSchool(school);
        teacherRepository.save(teacher);
    }

    public void asignCourseToTeacher(String username, Long courseId, String title) {
        Course course = courseRepository.findByIdAndTitle(courseId, title);
        Teacher teacher = teacherRepository.findByUsername(username);
        List<Course> courses = teacher.getCourse();
        courses.add(course);
        teacher.setCourse(courses);
        teacherRepository.save(teacher);
    }


}
