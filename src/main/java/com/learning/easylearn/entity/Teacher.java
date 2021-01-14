package com.learning.easylearn.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Teacher")
@Data
@NoArgsConstructor
public class Teacher extends BaseEntity {
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SchoolId")
    private School school;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "TeacherCourse",
    joinColumns = @JoinColumn(name = "TeacherId"),
    inverseJoinColumns = @JoinColumn(name = "CourseId"))
    private List<Course> course = new ArrayList<>();
}
