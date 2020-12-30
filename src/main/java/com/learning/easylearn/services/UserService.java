package com.learning.easylearn.services;

import com.learning.easylearn.entity.User;
import com.learning.easylearn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByFirstName(String name) {
        return userRepository.findByFirstName(name);
    }
}
