package com.learning.easylearn.services;

import com.learning.easylearn.DTO.SchoolDto;
import com.learning.easylearn.DTO.UserLoginDto;
import com.learning.easylearn.DTO.UserRegisterDto;
import com.learning.easylearn.controller.SchoolAdminController;
import com.learning.easylearn.entity.School;
import com.learning.easylearn.entity.SchoolAdmin;
import com.learning.easylearn.repository.SchoolAdminRepository;
import com.learning.easylearn.repository.SchoolRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolAdminService {
    @Autowired
    DozerBeanMapper mapper;
    private SchoolAdminRepository schoolAdminRepository;
    private SchoolRepository schoolRepository;

    @Autowired
    public void setSchoolRepository(SchoolAdminRepository schoolAdminRepository,
                                    SchoolRepository schoolRepository) {
        this.schoolAdminRepository = schoolAdminRepository;
        this.schoolRepository = schoolRepository;
    }

    public Boolean exist(String username) {
        return this.schoolAdminRepository.existsSchoolAdminByUsername(username);
    }

    public UserLoginDto login(String username, String password) {
        if (schoolAdminRepository.findByUsernameAndPassword(username, password) != null) {
            return mapper.map(schoolAdminRepository.findByUsernameAndPassword(username, password), UserLoginDto.class);
        } else {
            return null;
        }
    }

    public void register(UserRegisterDto userRegisterDto) {
        SchoolAdmin schoolAdmin = mapper.map(userRegisterDto, SchoolAdmin.class);
        this.schoolAdminRepository.save(schoolAdmin);
    }

    public void deleteSchoolAdmin(Long id){
        Optional<SchoolAdmin> schoolAdmin = schoolAdminRepository.findById(id);
        schoolAdmin.ifPresent(value -> {
            School school = schoolRepository.findByAdmin(value);
            school.setAdmin(null);
            schoolRepository.save(school);
            schoolAdminRepository.delete(value);
        });
    }

    public void updateName(String firstName, String lastName, String username) {
        SchoolAdmin schoolAdmin = schoolAdminRepository.findByUsername(username);
        schoolAdmin.setFirstName(firstName);
        schoolAdmin.setLastName(lastName);
        schoolAdminRepository.save(schoolAdmin);
    }

}
