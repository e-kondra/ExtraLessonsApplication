package com.extralessonsapplication.student;


import com.extralessonsapplication.school.SchoolEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
   List<StudentEntity> findAll();
   List<StudentEntity> findAllBySchoolEqualsAndIsActiveTrue(SchoolEntity school);
   StudentEntity findByPersonalCodeEquals(String personalCode);
}

