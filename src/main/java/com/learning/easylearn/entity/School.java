package com.learning.easylearn.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "School")
@Data
@NoArgsConstructor
public class School extends BaseEntity {

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "adminId")
    private SchoolAdmin admin;

}
