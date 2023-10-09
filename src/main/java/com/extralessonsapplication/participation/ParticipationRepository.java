package com.extralessonsapplication.participation;

import com.extralessonsapplication.lesson.LessonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ParticipationRepository extends CrudRepository<Participation, Long> {
    ArrayList<Participation> findAllByLessonEquals(LessonEntity lesson);
}
