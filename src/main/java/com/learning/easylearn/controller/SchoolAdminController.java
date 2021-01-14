package com.learning.easylearn.controller;

import com.learning.easylearn.services.SchoolAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schooladmin")
public class SchoolAdminController {
    SchoolAdminService schoolAdminService;

    @Autowired
    public void setCourseService(SchoolAdminService schoolAdminService) {
        this.schoolAdminService = schoolAdminService;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteSchoolAdmin (@RequestParam(name = "delete") Long id) {
        schoolAdminService.deleteSchoolAdmin(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
