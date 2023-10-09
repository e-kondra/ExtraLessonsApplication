package com.extralessonsapplication.lesson;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LessonRepository extends CrudRepository<LessonEntity, Long> {

}

