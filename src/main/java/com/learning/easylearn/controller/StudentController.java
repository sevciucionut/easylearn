package com.learning.easylearn.controller;

import com.learning.easylearn.DTO.UserLoginDto;
import com.learning.easylearn.DTO.UserRegisterDto;
import com.learning.easylearn.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteStudent(@RequestParam(name = "delete") Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/school")
    public ResponseEntity<Void> asignSchoolToStudent(@RequestParam(name = "username") String username,
                                                     @RequestParam(name = "schoolId") Long schoolId,
                                                     @RequestParam(name = "title") String title) {
        studentService.asignSchoolToStudent(username, schoolId, title);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/course")
    public ResponseEntity<Void> asignCourseToStudent(@RequestParam(name = "username") String username,
                                                     @RequestParam(name = "courseId") Long courseId,
                                                     @RequestParam(name = "title") String title) {
        studentService.asignCourseToStudent(username, courseId, title);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
