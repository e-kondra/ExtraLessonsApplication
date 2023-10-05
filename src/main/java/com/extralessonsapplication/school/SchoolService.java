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


}
