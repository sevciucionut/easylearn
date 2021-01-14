package com.learning.easylearn.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Student")
@Data
@NoArgsConstructor
public class Student extends BaseEntity {
    private static final long serialVersionUID = 2538014070726790377L;
    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Type")
    private String type;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "SchoolId")
    private School school;

    @ManyToMany
    @JoinTable(name = "StudentCourse",
            joinColumns = @JoinColumn(name = "StudentId"),
            inverseJoinColumns = @JoinColumn(name = "CourseId"))
    private List<Course> course;
}
