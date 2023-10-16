package com.extralessonsapplication.lesson;

import com.extralessonsapplication.participation.ParticipationService;
import com.extralessonsapplication.school.SchoolEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ParticipationService participationService;

    public LessonService(LessonRepository lessonRepository, ParticipationService participationService){
        this.lessonRepository=lessonRepository;
        this.participationService = participationService;
    }

    public ArrayList<LessonEntity> getAllLessons() {
        ArrayList<LessonEntity> lessonsList =(ArrayList<LessonEntity>) this.lessonRepository.findAll();
        lessonsList.sort(Comparator.comparing(LessonEntity::getDate).reversed());
        return lessonsList;
    }

    public void createLesson(LessonEntity lessonEntity) {
        this.lessonRepository.save(lessonEntity);
    }
    public LessonEntity createLessonObj(LessonEntity lessonEntity) {
        return this.lessonRepository.save(lessonEntity);
    }

    public LessonEntity getLessonById(Long lessonId) throws Exception{
        return this.lessonRepository.findById(lessonId).orElseThrow();
    }

    public void updateLesson(LessonEntity updatedLesson) {
        updatedLesson.setIsActive( updatedLesson.getIsActive()!= null);
        this.lessonRepository.save(updatedLesson);
    }

    public ArrayList<LessonEntity> getLessonsBySchool(SchoolEntity school){
        ArrayList<LessonEntity> lessonsList = (ArrayList<LessonEntity>) this.lessonRepository
                .findAllBySchoolEqualsAndIsActiveTrue(school);
        lessonsList.sort(Comparator.comparing(LessonEntity::getDate).reversed());
        return lessonsList;
    }


}
