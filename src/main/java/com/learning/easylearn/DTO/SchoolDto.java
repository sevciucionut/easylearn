package com.learning.easylearn.DTO;

import com.learning.easylearn.entity.SchoolAdmin;
import lombok.Data;

@Data
public class SchoolDto {
    private String description;
    private String title;
    private SchoolAdmin schoolAdmin;
}
