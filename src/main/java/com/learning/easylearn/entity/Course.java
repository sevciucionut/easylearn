package com.learning.easylearn.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Course")
@Data
@NoArgsConstructor
public class Course extends BaseEntity {

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

}
