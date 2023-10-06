package com.extralessonsapplication.lesson;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<LessonEntity, Long> {
}

