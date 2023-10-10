package com.extralessonsapplication.lesson;


import com.extralessonsapplication.school.SchoolEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LessonRepository extends CrudRepository<LessonEntity, Long> {
    ArrayList<LessonEntity> findAllBySchoolEqualsAndIsActiveTrue(SchoolEntity school);

}

