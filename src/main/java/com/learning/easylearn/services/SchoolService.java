package com.learning.easylearn.services;

import com.learning.easylearn.DTO.CourseDto;
import com.learning.easylearn.DTO.GetSchoolDto;
import com.learning.easylearn.DTO.SchoolDto;
import com.learning.easylearn.DTO.UserLoginDto;
import com.learning.easylearn.entity.School;
import com.learning.easylearn.repository.SchoolAdminRepository;
import com.learning.easylearn.repository.SchoolRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    @Autowired
    DozerBeanMapper mapper;
    SchoolRepository schoolRepository;
    SchoolAdminRepository schoolAdminRepository;

    @Autowired
    public void setSchoolRepository(SchoolRepository schoolRepository,
                                    SchoolAdminRepository schoolAdminRepository) {
        this.schoolRepository = schoolRepository;
        this.schoolAdminRepository = schoolAdminRepository;
    }

    public Optional<School> createSchool(SchoolDto schoolDto) {
        School school = mapper.map(schoolDto, School.class);
        schoolRepository.save(school);
        return schoolRepository.findById(school.getId());
    }

    public List<GetSchoolDto> findAll(){
        return  schoolRepository.findAll().stream().map(school -> mapper.map(school, GetSchoolDto.class)).collect(Collectors.toList());

    }


    public void asignAdmin(String adminUsername, Long schoolId, String schoolTitle) {
        School school = schoolRepository.findByIdAndTitle(schoolId, schoolTitle);
        school.setAdmin(schoolAdminRepository.findByUsername(adminUsername));
        schoolRepository.save(school);
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}
