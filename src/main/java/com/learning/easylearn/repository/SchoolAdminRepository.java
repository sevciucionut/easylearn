package com.learning.easylearn.repository;

import com.learning.easylearn.entity.SchoolAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolAdminRepository extends JpaRepository<SchoolAdmin, Long> {
    SchoolAdmin findByUsernameAndPassword(String username, String password);
    Boolean existsSchoolAdminByUsername(String username);
    SchoolAdmin findByUsername(String username);

}
