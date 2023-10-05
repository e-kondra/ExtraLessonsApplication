package com.extralessonsapplication.student;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public ArrayList<StudentEntity> getAllUsers() {return (ArrayList<StudentEntity>) this.studentRepository.findAll();
    }

    public void createStudent(StudentEntity studentEntity) throws Exception{
        this.studentRepository.save(studentEntity);
    }

}
