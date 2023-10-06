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

    public void createSchool(LessonEntity lessonEntity) throws Exception{
        this.lessonRepository.save(lessonEntity);
    }
    public ArrayList<LessonEntity> getAllLessons() {
        return (ArrayList<LessonEntity>) this.lessonRepository.findAll();
    }

}
