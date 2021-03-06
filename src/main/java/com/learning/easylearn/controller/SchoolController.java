package com.learning.easylearn.controller;

import com.learning.easylearn.DTO.GetSchoolDto;
import com.learning.easylearn.DTO.SchoolDto;
import com.learning.easylearn.DTO.UserLoginDto;
import com.learning.easylearn.entity.School;
import com.learning.easylearn.entity.SchoolAdmin;
import com.learning.easylearn.repository.SchoolAdminRepository;
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
    SchoolAdminRepository schoolAdminRepository;

    @Autowired
    public void setSchoolService(SchoolService schoolService, SchoolAdminRepository schoolAdminRepository) {
        this.schoolService = schoolService;
        this.schoolAdminRepository = schoolAdminRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<School> createSchool(@RequestParam(name = "username") String username,
                                               @RequestParam(name = "title") String tile,
                                               @RequestParam(name = "description") String description) {
        if (schoolAdminRepository.existsByUsername(username)) {
            return new ResponseEntity<School>(schoolService.createSchool(username, tile, description), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
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
