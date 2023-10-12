package com.extralessonsapplication.user;

import com.extralessonsapplication.student.StudentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;
    private String email;
    private Boolean isActive;
    @OneToOne
    private StudentEntity student;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public UserEntity(String username, String password, String name, String lastname, UserRole role, String email, Boolean isActive, StudentEntity student, Timestamp createdAt, Timestamp updatedAt) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.role = role;
        this.email = email;
        this.isActive = isActive;
        this.student = student;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return name +" "+ lastname;
    }

    @PrePersist // this will allow the method to be called before a chat Object is saved to Database
    public void beforeSave(){
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate // this will allow the method to be called before a chat Object is saved to Database
    public void beforeUpdate(){
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

}
