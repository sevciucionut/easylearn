package com.learning.easylearn.services;

import com.learning.easylearn.DTO.UserLoginDto;
import com.learning.easylearn.DTO.UserRegisterDto;
import com.learning.easylearn.entity.Student;
import com.learning.easylearn.repository.StudentRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    DozerBeanMapper mapper;
    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
}
