package com.extralessonsapplication.school;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository){
        this.schoolRepository=schoolRepository;
    }

    public void createSchool(SchoolEntity schoolEntity) throws Exception{
        this.schoolRepository.save(schoolEntity);
    }
    public ArrayList<SchoolEntity> getAllSchools() {
        return (ArrayList<SchoolEntity>) this.schoolRepository.findAll();
    }


    public SchoolEntity getSchoolById(Long id) throws Exception {
        return this.schoolRepository.findById(id).orElseThrow();
    }

    public void updateSchool(SchoolEntity school) throws Exception{
        this.schoolRepository.save(school);
    }
}
