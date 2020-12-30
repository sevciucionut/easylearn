package com.learning.easylearn.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
public class User extends BaseEntity {

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

    public static enum UserType {
        A, SA, T, S
    }
}