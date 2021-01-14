package com.learning.easylearn.repository;

import com.learning.easylearn.entity.School;
import com.learning.easylearn.entity.SchoolAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    Boolean existsByAdmin_Id(Long id);

    Optional<School> findById(Long id);
    School findByIdAndTitle(Long id, String title);
    School findByAdmin(SchoolAdmin admin);

}