package com.learning.easylearn.controller;

import com.learning.easylearn.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    TeacherService teacherService;

    @Autowired
    public void setTeacherService (TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTeacher(@RequestParam(name = "delete") Long id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
