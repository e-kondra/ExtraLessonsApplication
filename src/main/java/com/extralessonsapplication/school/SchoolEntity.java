package com.extralessonsapplication.school;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SchoolEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Boolean isActive;
    private String address;
    private String phone;

    public SchoolEntity(String name, Boolean isActive, String address, String phone) {
        this.name = name;
        this.isActive = isActive;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return  name ;
    }
}
