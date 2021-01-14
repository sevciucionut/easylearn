package com.learning.easylearn.controller;

import com.learning.easylearn.DTO.UserLoginDto;
import com.learning.easylearn.DTO.UserRegisterDto;
import com.learning.easylearn.services.SchoolAdminService;
import com.learning.easylearn.services.StudentService;
import com.learning.easylearn.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class LoginController {
    StudentService studentService;
    TeacherService teacherService;
    SchoolAdminService schoolAdminService;

    @Autowired
    public void setStudentService(
            StudentService studentService,
            TeacherService teacherService,
            SchoolAdminService schoolAdminService
    ) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.schoolAdminService = schoolAdminService;
    }

    @GetMapping("/login")
    public ResponseEntity<UserLoginDto> login(@RequestParam(name = "user") String username, @RequestParam(name = "password") String password) {
        if (schoolAdminService.login(username, password) != null) {
            return new ResponseEntity<UserLoginDto>(schoolAdminService.login(username, password), HttpStatus.OK);
        } else if (teacherService.login(username, password) != null) {
            return new ResponseEntity<UserLoginDto>(teacherService.login(username, password), HttpStatus.OK);
        } else if (studentService.login(username, password) != null) {
            return new ResponseEntity<UserLoginDto>(this.studentService.login(username, password), HttpStatus.OK);
        } else {
            return new ResponseEntity<UserLoginDto>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRegisterDto userRegisterDto) {
        if (!schoolAdminService.exist(userRegisterDto.getUsername()) &&
                !teacherService.exist(userRegisterDto.getUsername()) &&
                !studentService.exist(userRegisterDto.getUsername())) {
            switch (userRegisterDto.getType()) {
                case "student":
                    studentService.register(userRegisterDto);
                    return new ResponseEntity<>(HttpStatus.OK);
                case "teacher":
                    teacherService.register(userRegisterDto);
                    return new ResponseEntity<>(HttpStatus.OK);
                case "schooladmin":
                    schoolAdminService.register(userRegisterDto);
                    return new ResponseEntity<>(HttpStatus.OK);
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
