package com.extralessonsapplication.participation;

import com.extralessonsapplication.lesson.LessonEntity;
import com.extralessonsapplication.student.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public void createLessonsParticipations(LessonEntity lesson,
                                            Map<String, String> participatedStudents,
                                            ArrayList<StudentEntity> students) {
        for(StudentEntity student: students){
            Participation participation = new Participation(
                    student,
                    lesson,
                    participatedStudents.containsKey(student.getId().toString()));
            this.participationRepository.save(participation);
        }
    }

    public ArrayList<Participation> getParticipationsByLesson(LessonEntity lesson){
        return this.participationRepository.findAllByLessonEquals(lesson);
    }

    public void updateLessonsParticipations(LessonEntity updatedLesson,
                                            Map<String, String> editedParticipations,
                                            ArrayList<Participation> participations) {
        for(Participation part: participations){
            part.setAttended(editedParticipations.containsKey(part.getId().toString()));
            this.participationRepository.save(part);
        }
    }
}
