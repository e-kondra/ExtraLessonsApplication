package com.extralessonsapplication.user;

import com.extralessonsapplication.student.StudentEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.ArrayList;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public void createUser(UserEntity userEntity) throws Exception{
        this.userRepository.save(userEntity);
    }

    public ArrayList<UserEntity> getAllUsers() {
        return (ArrayList<UserEntity>) this.userRepository.findAll();
    }

    public UserEntity verifyUser(String username, String password)throws Exception {
        UserEntity user = this.userRepository.findByUsernameAndPassword(username, password);
        return user;

    }

    public UserEntity findUserById(Long id) throws Exception{
        return this.userRepository.findById(id).orElseThrow();
    }

    public void updateUser(UserEntity userEntity) throws Exception{
        userEntity.setIsActive( userEntity.getIsActive()!= null);
        this.userRepository.save(userEntity);
    }

    public UserEntity findUserByStudent(StudentEntity studentEntity) throws Exception {
        return this.userRepository.findByStudentAndIsActiveTrue(studentEntity);
    }

    public UserEntity getCurrentUserByCookies(@CookieValue(value = "loggedInUserId", defaultValue = "") String userId){
        try {
            return this.userRepository.findById(Long.parseLong(userId)).orElseThrow();
        }catch (Exception e){
            return null;
        }
    }

    public ArrayList<UserEntity> getAllActiveTeachers(){
        return this.userRepository.findAllByRoleEqualsAndIsActiveTrue(UserRole.TEACHER);
    }

    public Boolean isUserModerator(UserEntity user){
        return user.getRole().toString().equals("MODERATOR");
    }

    public Boolean isUserTeacher(UserEntity user){
        return user.getRole().toString().equals("TEACHER");
    }

    public Boolean isUserParent(UserEntity user) {return user.getRole().toString().equals("PARENT");}

    public UserEntity findActiveModerator() throws Exception{
        return this.userRepository.findFirstByIsActiveTrueAndRoleEquals(UserRole.MODERATOR);
    }


}
