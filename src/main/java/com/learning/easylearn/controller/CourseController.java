package com.learning.easylearn.controller;

import com.learning.easylearn.DTO.CourseDto;
import com.learning.easylearn.entity.Teacher;
import com.learning.easylearn.repository.TeacherRepository;
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
    TeacherRepository teacherRepository;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseDto>> all() {
        return new ResponseEntity<List<CourseDto>>(courseService.listAllCourses(), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<CourseDto> postCourse(@RequestParam(name = "username") String username,
                                                @RequestParam(name = "title") String tile,
                                                @RequestParam(name = "description") String description) {
        if (teacherRepository.existsByUsername(username)) {
            courseService.postCourse(username, tile, description);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CourseDto> deleteCourse(@RequestParam(name = "delete") Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/rename")
    public ResponseEntity<Void> renameCourse(@RequestParam(name = "title") String title,
                                             @RequestParam(name = "description") String description,
                                             @RequestParam(name = "title") String currentTitle,
                                             @RequestParam(name = "description") String currentDescription) {
        courseService.renameCourse(title, description, currentTitle, currentDescription);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
