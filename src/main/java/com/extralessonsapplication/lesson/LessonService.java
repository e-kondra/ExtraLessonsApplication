package com.extralessonsapplication.lesson;

import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.school.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository){
        this.lessonRepository=lessonRepository;
    }

    public ArrayList<LessonEntity> getAllLessons() {
        return (ArrayList<LessonEntity>) this.lessonRepository.findAll();
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
}
