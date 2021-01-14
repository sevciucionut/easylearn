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

import java.util.*;

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
        Optional<Teacher> teacher = teacherRepository.findById(id);
        teacher.ifPresent(value -> {
            value.setCourse(null);
            value.setSchool(null);
            teacherRepository.delete(value);
        });
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
        if (teacher.getCourse() != null) {
            courses = teacher.getCourse();
        }
        courses.add(course);
        teacher.setCourse(courses);
        teacherRepository.save(teacher);
    }


}
