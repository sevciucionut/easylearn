package com.learning.easylearn.controller;

import com.learning.easylearn.entity.User;
import com.learning.easylearn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class UserController {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<User> forza(@RequestParam String ceva) {
        return new ResponseEntity<User> (userService.getUserByFirstName(ceva), HttpStatus.OK);
    }


}
