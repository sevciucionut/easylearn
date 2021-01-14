package com.learning.easylearn.DTO;

import lombok.Data;

@Data
public class UserLoginDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String type;
    private String username;
}
