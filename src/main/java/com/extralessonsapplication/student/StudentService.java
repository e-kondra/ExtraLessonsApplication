package com.extralessonsapplication.student;

import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.user.UserEntity;
import com.extralessonsapplication.user.UserRole;
import com.extralessonsapplication.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StudentService {
    private UserService userService;
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
        this.userService=userService;
    }

    public void createStudent(StudentEntity studentEntity) throws Exception{
        this.studentRepository.save(studentEntity);
    }
    public ArrayList<StudentEntity> getAllStudents() {return (ArrayList<StudentEntity>) this.studentRepository.findAll();
    }

    public void createParentByTeacher(StudentEntity studentEntity) throws Exception{
        Optional<UserEntity> parent = Optional.ofNullable(this.userService.findUserByStudent(studentEntity));
        if(parent.isEmpty()){ //parent creation
            UserEntity userParent = new UserEntity();
            userParent.setRole(UserRole.PARENT);
            userParent.setUsername(studentEntity.getPersonalCode());
            userParent.setPassword("12345");
            userParent.setIsActive(true);
            userParent.setStudent(studentEntity);
            this.userService.createUser(userParent);
        } else {
            UserEntity userParent = this.userService.findUserByStudent(studentEntity);
            userParent.setIsActive(true);
            this.userService.updateUser(userParent);
        }
    }

    public ArrayList<StudentEntity> getAllActiveStudentsBySchool(SchoolEntity school) throws Exception {
        return (ArrayList<StudentEntity>) this.studentRepository.findAllBySchoolEqualsAndIsActiveTrue(school);
    }

    public StudentEntity findStudentById(Long id) throws Exception{
            return this.studentRepository.findById(id).orElseThrow();
    }

    public void updateStudent(StudentEntity studentEntity) throws Exception{
        this.studentRepository.save(studentEntity);
        }
}


