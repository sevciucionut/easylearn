package com.learning.easylearn.controller;

import com.learning.easylearn.DTO.GetSchoolDto;
import com.learning.easylearn.DTO.SchoolDto;
import com.learning.easylearn.DTO.UserLoginDto;
import com.learning.easylearn.entity.School;
import com.learning.easylearn.entity.SchoolAdmin;
import com.learning.easylearn.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school")
public class SchoolController {
    SchoolService schoolService;

    @Autowired
    public void setSchoolService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<School>> createSchool(@RequestBody SchoolDto schoolDto) {
        return new ResponseEntity<Optional<School>>(schoolService.createSchool(schoolDto), HttpStatus.OK);
    }

    @PutMapping("/admin")
    public void setAdmin(@RequestParam(name = "adminUsername") String adminUsername,
                         @RequestParam(name = "schoolId") Long schoolId,
                         @RequestParam(name = "schoolTitle") String schoolTitle) {
        schoolService.asignAdmin(adminUsername, schoolId, schoolTitle);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetSchoolDto>> getAll() {
        return new ResponseEntity<List<GetSchoolDto>>(schoolService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteSchool(@RequestParam(name = "delete") Long id) {
        schoolService.deleteSchool(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
