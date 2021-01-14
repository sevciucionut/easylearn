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

import java.util.*;

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
        List<Course> courses = new List<Course>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Course> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Course course) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Course> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Course> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Course get(int index) {
                return null;
            }

            @Override
            public Course set(int index, Course element) {
                return null;
            }

            @Override
            public void add(int index, Course element) {

            }

            @Override
            public Course remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Course> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Course> listIterator(int index) {
                return null;
            }

            @Override
            public List<Course> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        if (student.getCourse() != null) {
            courses = student.getCourse();
        }
        courses.add(course);
        student.setCourse(courses);
        studentRepository.save(student);
    }
}
