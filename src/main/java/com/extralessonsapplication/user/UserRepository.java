package com.extralessonsapplication.user;

import com.extralessonsapplication.student.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsernameAndPassword(String username, String password);

    UserEntity findByStudentAndIsActiveTrue(StudentEntity student);
    ArrayList<UserEntity> findAllByRoleEqualsAndIsActiveTrue(UserRole role);
}
