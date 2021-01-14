package com.learning.easylearn.DTO;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String firstName;
    private String lastName;
    private String type;
    private String username;
    private String password;
}
