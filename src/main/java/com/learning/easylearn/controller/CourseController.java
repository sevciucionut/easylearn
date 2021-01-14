package com.learning.easylearn.controller;

import com.learning.easylearn.DTO.CourseDto;
import com.learning.easylearn.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseDto>> all() {
        return new ResponseEntity<List<CourseDto>>(courseService.listAllCourses(), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<CourseDto> postCourse(@RequestBody CourseDto courseDto) {
        courseService.postCourse(courseDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CourseDto> postCourse(@RequestParam(name = "delete") Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
