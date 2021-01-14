package com.learning.easylearn.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SchoolAdmin")
@Data
@NoArgsConstructor
public class SchoolAdmin extends BaseEntity {
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

    @OneToOne(
            mappedBy = "admin",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private School school;

}
